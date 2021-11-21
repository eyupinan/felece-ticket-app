function create_alert(success_msg,err_msg){
    var url_string =  window.location;
    var url = new URL(url_string);
    var err = url.searchParams.get("err");
    if (err!=null || err!= undefined){
        if (err=="true"){
            msg=$("#msg")
            msg.attr("hidden",false)
            msg.html(err_msg)
            msg.addClass( "label label-danger alert" );

        }
        if (err==="false"){
            msg=$("#msg")
            msg.attr("hidden",false)
            msg.html(success_msg)
            msg.addClass( "label label-success alert" );


        }
    }
    setTimeout(function(){
        $("#msg").attr("hidden",true)
    },3000)
}