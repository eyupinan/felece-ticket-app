function getDestinationContent(select_id){
    $.ajax({
            url: window.location.protocol+ "//" +window.location.host +  destination_path,
            type: "get",
            success: function(res) {
                select_option_adder(res,select_id,"name")
            }
    })
}
function getVehicleContent(select_id){
    $.ajax({
            url: window.location.protocol+ "//" +window.location.host +  vehicle_path,
            type: "get",
            success: function(res) {
                select_option_adder(res,select_id,"plate")
            }
    })
}
function select_option_adder(res,select_id,field_name){
    var select = $("#"+select_id);

    $(function() {
        $.each(res, function(i, item) {
            var opt = $("<option>")
            opt.value=item[field_name]
            opt.text=item[field_name]
            opt.html(item[field_name])
            select.append(opt.clone());
        })
        var options = $('#'+select_id+' option');
        var arr = options.map(function(_, o) { return { t: $(o).text(), v: o.value }; }).get();
        arr.sort(function(o1, o2) { return o1.t > o2.t ? 1 : o1.t < o2.t ? -1 : 0; });
        options.each(function(i, o) {
          o.value = arr[i].v;
          $(o).text(arr[i].t);
        });
        var default_opt = $("<option value='empty'>")

        default_opt.attr("selected",true)
        default_opt.html("select")
        select.append(default_opt.clone())
    })
}
function dateSynchronizer(){
    date_el=$("#startDate")[0]
    time_el=$("#startTime")[0]
    current_date = new Date()
    var month=current_date.getMonth()+1
    date =current_date.getFullYear().toString()+"-"+month.toString()+"-"+current_date.getDate().toString()
    var hour =current_date.getHours().toString();
    var minutes = current_date.getMinutes().toString();
    if (hour.length==1){
        hour = "0"+hour
        console.log(hour)
    }
    if (minutes.length==1){
        minutes = "0"+minutes
    }
    time =  hour+":"+minutes
    date_el.value=date
    date_el.min=date
    time_el.value=time

}
function body_generator(){
    body ={}
    startDestinationName=$("#select_start_destinations")[0].value
    endDestinationName=$("#select_end_destinations")[0].value
    date=$("#startDate")[0].value
    time=$("#startTime")[0].value
    price=parseInt($("#price")[0].value)
    vehiclePlate=$("#vehiclePlate")[0].value
    if (startDestinationName!=undefined && startDestinationName!=""){
        body.startDestinationName=startDestinationName
    }
    if (endDestinationName!=undefined && endDestinationName!=""){
        body.endDestinationName=endDestinationName
    }
    if (date!=undefined && time!=undefined){
        date=date + " " + time+":00"
        body.date = date
    }
    if (!isNaN(price)){
        body.price = price
    }
    if (vehiclePlate!=undefined && vehiclePlate!=""){
        body.vehiclePlate=vehiclePlate
    }
    return body
}
function onSubmit(){
     body = body_generator();
     url =   window.location.protocol+ "//" +window.location.host + post_path
     $.ajax({
                url: url,
                type: "post",
                contentType:"application/json",
                data : JSON.stringify(body),
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
    getDestinationContent("select_start_destinations")
    getDestinationContent("select_end_destinations")
    getVehicleContent("vehiclePlate")
    dateSynchronizer()
    create_alert("Route created successfully!","Error occured while creating route!")
})