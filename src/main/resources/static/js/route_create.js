function getDestinationContent(id){
    $.ajax({
            url: destination_url,
            type: "get",
            success: function(res) {
                destination_option_adder(res,id)
            }
    })
}
function destination_option_adder(res,id){
    select = $("#"+id);
    $(function() {
        $.each(res, function(i, item) {
            var opt = $("<option>")
            opt.value=item.name
            opt.text=item.name
            opt.html(item.name)
            select.append(opt.clone());
        })
        var options = $('#'+id+' option');
        var arr = options.map(function(_, o) { return { t: $(o).text(), v: o.value }; }).get();
        arr.sort(function(o1, o2) { return o1.t > o2.t ? 1 : o1.t < o2.t ? -1 : 0; });
        options.each(function(i, o) {
          o.value = arr[i].v;
          $(o).text(arr[i].t);
        });
        var default_opt = $("<option value='empty'>")

        default_opt.attr("selected",true)
        default_opt.html("select destination")
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
     url =  post_url
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
    dateSynchronizer()
    create_alert("Route created successfully!","Error occured while creating route!")
})