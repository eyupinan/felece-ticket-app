function body_generator(){
    body={}
    username = $("#username_input")[0].value
    phone = parseInt($("#phone_input")[0].value)
    address = $("#address_input")[0].value
    email = $("#email_input")[0].value
    password = $("#password_input")[0].value

    if (username!==undefined && username!=''){
        body.username=username
    }
    if (!isNaN(phone)){
        body.phone=phone
    }
    if (address!==undefined && address!==""){
        body.address=address
    }
    if (email!==undefined && email!=''){
        body.email=email
    }
    if (password!==undefined && password!=''){
        body.password=password
    }
    return body


}
function action(button,column_name){
    button = $(button)
    inputElement = $("#"+column_name+"_input")
    if (inputElement[0]==undefined){
        inputElement = $("."+column_name+"_input")
    }
    console.log(inputElement)
    labelElement = $("#"+column_name)
    inputElement.removeAttr('hidden');
    labelElement.attr("hidden",true);
    inputElement.each(function (index){
        element = $(this)
        console.log(element)

    })

    button.html("save")
    button.attr("onclick","putRequest()");

}
function putRequest(){

    body = body_generator()
    console.log(body)
    console.log(update_url+"?"+$.param(body))
     $.ajax({
            url: update_url+"?"+$.param(body),
            type: "put",
            contentType:"application/x-www-form-urlencoded",
            success: function(res) {
                var current_url = window.location;
               var url = new URL(current_url);
               url.searchParams.set('err', "false");
               window.location.href=url
            },
            error:function(){
                var current_url = window.location;
               var url = new URL(current_url);
               url.searchParams.set('err', "true");
               window.location.href=url
            }
        });
}
function deleteRequest(id){
    url = url_generator()
    $.ajax({
            url: url,
            type: "delete",
            success: function(res) {
                var current_url = window.location;
               var url = new URL(current_url);
               url.searchParams.set('err', "false");
               window.location.href=url
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

    create_alert("user updated successfully!","error occured while updating user")
})
