function body_generator(){
    body ={}
    username=$("#username")[0].value
    phone=$("#phone")[0].value
    address=$("#address")[0].value
    email=$("#email")[0].value
    role=$("#role")[0].value
    password=$("#password")[0].value
    if (username!=undefined && username!=""){
        body.username=username
    }
    if (phone!=undefined && phone!=""){
        body.phone=phone
    }
    if (address!=undefined && address!=""){
        body.address=address
    }
    if (email!=undefined && email!=""){
        body.email=email
    }
    if (role!=undefined && role!=""){
        body.role=role
    }
    if (password!=undefined && password!=""){
        body.password=password
    }
    return body
}
function onSubmit(){
     body = body_generator();
     url =    window.location.protocol+ "//" +window.location.host + user_post_path
     console.log( url+"?"+$.param(body))
     $.ajax({
                url: url,
                type: "post",
                data:JSON.stringify(body),
                contentType:"application/json",
                success: function(res) {

                    window.location.href=user_path + "?err=false"
                },
                error:function(){
                    var current_url = window.location;
                    var url = new URL(current_url);
                    url.searchParams.set('err', "true");
                    window.location.href=url
                }
            });
}
$(document).ready(function(){
    create_alert("User created successfully!","error occured while creating user")
})