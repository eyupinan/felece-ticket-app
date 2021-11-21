package org.felecechallenge.ticket.facade;

import org.felecechallenge.ticket.enums.Roles;
import org.felecechallenge.ticket.exception.BadRequestException;
import org.felecechallenge.ticket.exception.ConflictException;
import org.felecechallenge.ticket.facade.converter.Converter;
import org.felecechallenge.ticket.facade.dto.user.NewUserData;
import org.felecechallenge.ticket.facade.dto.user.UserData;
import org.felecechallenge.ticket.facade.dto.user.UserTransferData;
import org.felecechallenge.ticket.facade.populator.user.UserAddressPopulator;
import org.felecechallenge.ticket.facade.populator.user.UserInfoPopulator;
import org.felecechallenge.ticket.facade.populator.user.UserNamePopulator;
import org.felecechallenge.ticket.filter.UserFilter;
import org.felecechallenge.ticket.model.User;
import org.felecechallenge.ticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacade {

    private Converter useDataConverter = new Converter(UserData.class);

    public UserFacade(){
        this.useDataConverter.addPopulator(new UserAddressPopulator());
        this.useDataConverter.addPopulator(new UserInfoPopulator());
        this.useDataConverter.addPopulator(new UserNamePopulator());
    }
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void validator(NewUserData data){
        if (data.getPassword()==null || data.getEmail()==null || data.getAddress()==null ||
                data.getPhone()==null || data.getUsername()==null){
            throw new BadRequestException();
        }
    }
    public UserData getUserDataByUserName(String username){
        User user = userService.getUserByName(username);
        return (UserData) useDataConverter.convert(user);
    }
    public UserData getUserDataById(Long id){
        User user = userService.getById(id);
        return (UserData) useDataConverter.convert(user);
    }
    public UserTransferData getUsersWithFilter(UserFilter filter){
        Page<User> userPage= userService.findByFilter(filter);
        List<User> userList = userPage.getContent();
        List<UserData> userDataList = this.useDataConverter.convertAll(userList);
        UserTransferData data = new UserTransferData();
        data.setUserData(userDataList);
        data.setPageCount(userPage.getTotalPages());
        return data;
    }
    public void saveUser(NewUserData data){
        User user = new User();
        validator(data);
        if (data.getUsername()!=null){
            try{
                User existedUser = this.userService.getUserByName(data.getUsername());
                if (existedUser!=null){
                    throw new Exception();
                }

            }catch (Exception e){
                throw new ConflictException();
            }
        }
        userDataMapper(user,data);


        this.userService.save(user);
    }
    public void updateUser(Long id, NewUserData data){
        User user;
        if (id!=null){
            user = this.userService.getById(id);
        }
        else if ( data.getId()!=null){
            id=data.getId();
            user = this.userService.getById(data.getId());
        }
        else{
            throw new BadRequestException();
        }
        if (data.getUsername()!=null){
                User existedUser = this.userService.getUserByName(data.getUsername());
                if (existedUser!=null){
                    if (id!=existedUser.getId()){
                        throw new ConflictException();
                    }
                }

        }
        userDataMapper(user,data);
        this.userService.save(user);
    }
    public void deleteUser(String username){
        this.userService.delete(username);
    }
    public void userDataMapper(User user,NewUserData data){
        if (data.getEmail()!=null){
            user.setEmail(data.getEmail());
        }
        if (data.getUsername()!=null){
            user.setUsername(data.getUsername());
        }
        if (data.getAddress()!=null){
            user.setAddress(data.getAddress());
        }
        if (data.getPhone()!=null){
            user.setPhone(data.getPhone());
        }
        if (data.getPhone()!=null){
            user.setRole(data.getRole());
        }
        if (data.getPassword()!=null){
            user.setPassword(passwordEncoder.encode(data.getPassword()));
        }
    }
}
