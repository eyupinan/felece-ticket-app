function getDestinationContent(select_id,value=""){
    $.ajax({
            url: window.location.protocol+ "//" +window.location.host + destination_path ,
            type: "get",
            success: function(res) {
                destination_option_adder(res,select_id,value)
            }
    })
}
function destination_option_adder(res,select_id,value){
    select = $("#"+select_id);
    $(function() {
        $.each(res, function(i, item) {
            var opt = $("<option>")
            console.log(opt)
            opt.value=item.name
            opt.text=item.name
            opt.html(item.name)
            select.append(opt.clone());
        })
        var options = $('#'+select_id+' option');
        var arr = options.map(function(_, o) { return { t: $(o).text(), v: o.value }; }).get();
        arr.sort(function(o1, o2) { return o1.t > o2.t ? 1 : o1.t < o2.t ? -1 : 0; });
        options.each(function(i, o) {
          o.value = arr[i].v;
          if (arr[i].v===value){
            $(o).attr("selected",true)
          }
          $(o).text(arr[i].t);
        });

    })
}
function url_generator(){
    base_url= window.location.protocol+ "//" +window.location.host + update_path
    return base_url
}
function body_generator(){
    body={}
    startDestinationName = $("#startDestinationName_input")[0].value
    endDestinationName = $("#endDestinationName_input")[0].value
    date = $(".date_input")[0].value
    time = $(".date_input")[1].value
    price = parseFloat($("#price_input")[0].value)
    startDestinationId = parseInt($("#startDestinationId_input")[0].value)
    endDestinationId = parseInt($("#endDestinationId_input")[0].value)
    vehiclePlate = $("#vehiclePlate_input")[0].value
    vehicleId = parseInt($("#vehicleId_input")[0].value)

    if (startDestinationName!=='empty' && startDestinationName!==undefined && startDestinationName!=''){
        body.startDestinationName=startDestinationName
    }
    if (endDestinationName!=='empty' && endDestinationName!==undefined && endDestinationName!=''){
        body.endDestinationName=endDestinationName
    }
    if (date!==undefined && date!=="" && time!=="" && time!=undefined){
        console.log("new date:",date+" "+time)
        body.date=date+" "+time+":00"
    }
    if (!isNaN(startDestinationId)){
        body.startDestinationId=startDestinationId
    }
    if (!isNaN(price)){
            body.price=price
        }
    if (!isNaN(endDestinationId)){
        body.endDestinationId=endDestinationId
    }
    if (vehiclePlate!=undefined && vehiclePlate!==''){
        body.vehiclePlate=vehiclePlate
    }
    if (!isNaN(vehicleId)){
        body.vehicleId=vehicleId
    }
    return body


}
function action(button,column_name,value=""){
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
        if (element.is("select")){
            console.log(element)
            getDestinationContent(column_name+"_input",value)
        }
        if (element.is("input[type='date']")){
            console.log(value.split(" ")[0].trim())
           element[0].value=value.split(" ")[0].trim()
        }
        if (element.is("input[type='time']")){
           console.log(value.split(" ")[1].trim())
           element[0].value=value.split(" ")[1].trim()
        }
    })

    button.html("save")
    button.attr("onclick","putRequest()");

}
function putRequest(){
    url = url_generator()
    body = body_generator()
    console.log(body)
     $.ajax({
            url: url,
            type: "put",
            contentType:"application/json",
            data: JSON.stringify(body),
            success: function(res) {
                location.reload();
            },
        });
}
function deleteRequest(id){
    url = url_generator()
    $.ajax({
            url: url,
            type: "delete",
           success: function(res) {
               var url = new URL(window.location.protocol + "//" + window.location.host + route_after_disable);
               url.searchParams.set('err', "false");
               window.location.href=url
           },
           error:function(){
               var url = new URL(window.location.protocol + "//" + window.location.host + route_after_disable);
               url.searchParams.set('err', "true");
               window.location.href=url
           }
        });
}
$(document).ready(function(){
    create_alert("Route updated successfully!","Error occured while updating route")
})
