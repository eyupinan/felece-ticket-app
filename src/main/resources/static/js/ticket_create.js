function getDestinationContent(){
    $.ajax({
            url:  window.location.protocol+ "//" +window.location.host +  destination_path,
            type: "get",
            success: function(res) {
                destination_option_adder(res)
            }
    })
}
function destination_option_adder(res){
    startSelect = $("#select_start_destinations");
    endSelect = $("#select_end_destinations");
    $(function() {
        $.each(res, function(i, item) {
            var opt = $("<option>")
            opt.value=item.name
            opt.text=item.name
            opt.html(item.name)
            startSelect.append(opt.clone());
            endSelect.append(opt.clone());
        })
        var start_options = $('#select_start_destinations option');
        var end_options = $('#select_start_destinations option');
        var arr = start_options.map(function(_, o) { return { t: $(o).text(), v: o.value }; }).get();
        arr.sort(function(o1, o2) { return o1.t > o2.t ? 1 : o1.t < o2.t ? -1 : 0; });
        start_options.each(function(i, o) {
          o.value = arr[i].v;
          $(o).text(arr[i].t);
        });
        end_options.each(function(i, o) {
            o.value = arr[i].v;
            $(o).text(arr[i].t);
        });
        var default_opt = $("<option value='empty'>")

        default_opt.attr("selected",true)
        default_opt.html("select destination")
        startSelect.append(default_opt.clone())
        endSelect.append(default_opt.clone())
    })
}
function get_routeData(selected_date){
    startDestination = $("#select_start_destinations")[0].value

    endDestination =$("#select_end_destinations")[0].value

    params={sortBy:"date"}
    if (startDestination!=null && startDestination!="empty"){
        params.startDestination=startDestination
    }
    if (endDestination!=null && endDestination!="empty"){
        params.endDestination=endDestination
        }
    $.ajax({
            url:  window.location.protocol+ "//" +window.location.host +  route_path+"?"+$.param(params),
            type: "get",
            success: function(res) {
                routeData = res.routeData
                if (params.startDestination!=undefined && params.endDestination!=undefined && routeData[0]!=undefined){
                    date_option_adder(true)
                }
                else{
                    empty_selects()
                }

            }
    })
}
function empty_selects(){
    var date = $("#date_select");
    var time =  $("#time_select");
    var routeId =  $("#routeId");
    date[0].innerHTML=""
    time[0].innerHTML=""
    routeId[0].value=""
    routeId.html("")
}
function date_option_adder(restart=false){
    var date = $("#date_select");
    var time =  $("#time_select");
    selected_date=date[0].value
    if (selected_date!=undefined || selected_date===""){
        date_part  = selected_date.split(" ")[0]
    }
    date[0].innerHTML=""
    time[0].innerHTML=""
    usedDateList=[]
    $(function() {
            first=true
            $.each(routeData, function(i, item) {
                var opt_date;
                if (selected_date==undefined || selected_date==="" || restart===true){
                    selected_date=item.date
                    date_part  = selected_date.split(" ")[0]
                    restart=false
                }
                if (!usedDateList.includes(item.date.split(" ")[0])){
                    if (item.date.split(" ")[0]===date_part){
                        opt_date = $("<option selected>")
                        opt_date.prop("selected",true)

                        date[0].value=item.date.split(" ")[0]
                    }
                    else{
                        opt_date = $("<option>")
                    }
                    opt_date[0].text=item.date.split(" ")[0]
                    opt_date.html(item.date.split(" ")[0])
                    date.append(opt_date);
                    usedDateList.push(item.date.split(" ")[0])
                }

                if (item.date.split(" ")[0]===date_part){
                    if (first==true){
                        var opt_time = $("<option selected>")
                        first=false
                    }
                    else{
                        var opt_time = $("<option>")
                    }

                    opt_time.value=item.date.split(" ")[1]
                    opt_time.text=item.date.split(" ")[1]
                    opt_time.html(item.date.split(" ")[1])
                    time.append(opt_time.clone());
                    time[0].value=item.date.split(" ")[1]
                }

            })
        });
    setTimeout(function(){calculate_new_routeId()},100)

}
function calculate_new_routeId(){
    var routeId;
    date_el = $("#date_select");
    time_el =  $("#time_select");
    date=date_el[0].value
    time=time_el[0].value
    if (time.split(":").length==1){
        time=time+":00:00"
    }
    else if (time.split(":").length==2){
        time=time+":00"
    }
    date = date+" "+time
    for (i=0;i<routeData.length;i++){
        if (date === routeData[i].date){
            routeId=routeData[i].id
        }
    }
    route_el = $("#routeId")
    route_el[0].value = parseInt(routeId)
}
function body_generator(){
    body ={}

    routeId=$("#routeId")[0].value
    username=$("#username")[0].value
    if (routeId!=undefined && routeId!=""){
        body.routeId=routeId
    }
    if (username!=undefined && username!=""){
        body.userName=username
    }
    return body
}
function onSubmit(){
     body = body_generator();
     url =   window.location.protocol+ "//" +window.location.host + ticket_post_path
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
    getDestinationContent()
    create_alert("ticket created successfully","Error occured while creating ticket")
})