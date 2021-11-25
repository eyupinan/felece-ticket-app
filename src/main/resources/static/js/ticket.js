pageCount=1
pageIndex=1
routeMap={}
function getDestinationContent(){
    $.ajax({
            url:   window.location.protocol+ "//" +window.location.host +  destination_path,
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

function dateSynchronizer(id,artis=0){
    date_el=$("#"+id)[0]
    current_date = new Date()
    current_date.setDate(current_date.getDate() + artis);
    var month=current_date.getMonth()+1
    if (month.toString().length==1){
        month="0"+month
    }
    var day = current_date.getDate()
    if (day.toString().length==1){
        day="0"+day
    }
    date =current_date.getFullYear().toString()+"-"+month.toString()+"-"+day.toString()
    date_el.value=date
    date_el.min=date

}
function url_generator(){
    query_obj={}
    var username;
    if ($("#search_by_username")[0]!=undefined){
        username=$("#search_by_username")[0].value
    }
    startDest=$("#select_start_destinations")[0].value
    endDest=$("#select_end_destinations")[0].value
    startDate=$("#startDate")[0].value
    endDate=$("#endDate")[0].value
    state=$("#state")[0].value
    limit=10
    query_obj.sortBy="route.date"
    query_obj.limit=limit
    query_obj.offset=limit* (pageIndex-1)
    if (username!=null && username!="" && username!=undefined){
		query_obj.username=username
	}
	if (startDest!=undefined && startDest!=="empty" && startDest!==""){
		query_obj.startDestination=startDest
	}
	if (endDest!=undefined && endDest!=="empty" && endDest!==""){
		query_obj.endDestination=endDest
	}
	if (startDate!=undefined){
		query_obj.startDate=startDate
	}
	if (endDate!=undefined){
    		query_obj.endDate=endDate
    }
    if (state!=undefined && state!="empty"){
        		query_obj.state=state
        }
    var str = $.param( query_obj );
    if (str!==""){
        url =   window.location.protocol+ "//" +window.location.host +  ticket_path +"?"+str
    }
    return url
}
function getPageData(){
    url = url_generator()
     $.ajax({
            url: url,
            type: "get",
            success: function(res) {
                ticketData = res.ticketData
                pageCount = res.pageCount
                $(".rows").attr("hidden", true)
                $(function() {
                    $.each(ticketData, function(i, item) {
                        if (!$("#index-" + i).length) {
                            row = $("#index").clone().attr("id", "index-" + i).removeAttr('hidden').appendTo("#table_body");
                        } else {
                            row = $("#index-" + i).removeAttr("hidden")
                        }
                        row.find("#id").text(item.id)
                        row.find("#username").text(item.userName)
                        row.find("#startDestinationName").text(item.startDestination)
                        row.find("#endDestinationName").text(item.endDestination)
                        row.find("#date").text(item.date)
                        row.find("#state_span").removeClass().addClass("label " + state_css(item.state)).text(item.state)
                        row.find("#routeId").text(item.routeId)

                        if (item.state=="CANCELED"){
                            row.find("#post_button").attr("disabled",true)
                            row.find("#post_button").attr("hidden",true)
                            row.find("#cancel_button").attr("disabled",true)
                            row.find("#cancel_button").attr("hidden",true)
                        }
                        else{
                            row.find("#post_button").attr("disabled",false)
                            row.find("#cancel_button").attr("disabled",false)
                            row.find("#post_button").attr("hidden",false)
                            row.find("#cancel_button").attr("hidden",false)
                            row.find("#post_button").attr("onclick","action(this,'postpone',"+item.id+","+i+")")
                            row.find("#cancel_button").attr("onclick","action(this,'cancel',"+item.id+","+i+")")
                        }


                    });
                });
                create_page_buttons(pageIndex,pageCount)

            }
        });
}
function create_page_buttons(active=1,page_count=1){

    table_foot=document.getElementById("page_buttons")
    table_foot.innerHTML=""
    html_str=""
    html_str+=`<ul class="pagination">
                    <li><a onclick="changePage('<')"><</a></li>`
    for (i=1;i<page_count+1;i++){
        html_str+=`<li><a class="${i==active ? "active" : ""}" href="#" onclick="changePage(${i})">${i}</a></li>`
    }
    html_str+=`<li><a onclick="changePage('>')">></a></li></ul>`
    table_foot.innerHTML=html_str
}
function state_css(state) {

    state = state.replaceAll(" ", "");
    cls = ""
    switch (state) {
        case "RECEIPT":
            cls = "label-success"
            break;
        case "CANCELED":
            cls = "label-danger"
            break;
        case "POSTPONED":
            cls = "label-warning"
            break;
    }
    return cls
}
function onSearch(){
    getPageData()
    changePage(1)
}
function changePage(page_ind){
    if (page_ind==="<"){
        if (pageIndex!=1){
            pageIndex=pageIndex-1
        }
    }
    if (page_ind===">"){
        if (pageIndex!=page_count){
                pageIndex=pageIndex+1
        }
    }
    else{
        pageIndex=page_ind
    }

    getPageData()
}
function action(button,action_type,id,row_index){
    button = $(button)[0]
    if (action_type==="cancel"){
        $.ajax({
                url:  window.location.protocol+ "//" +window.location.host +  update_path,
                type: "put",
                contentType:"application/json",
                data: JSON.stringify({id:id,state:"CANCELED"}),
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

        })
    }
    else if (action_type==="postpone"){
        row = $("#index-"+row_index)
        date_el = row.find("#date")
        cancel_button = row.find("#cancel_button")
        cancel_button.attr("hidden",true)

		startDestination_el = row.find("#startDestinationName")
		endDestination_el = row.find("#endDestinationName")
        startDestination=startDestination_el.html()
        endDestination=endDestination_el.html()
        $(button).html("save")
        $(button).attr("onclick","saveUpdate("+row_index+","+id+")")
		date=date_el.html()
        date_el.html("")
        new_html=`
        <select id="date_select" onchange="date_option_adder(${row_index})"></select>
        <select id="time_select"></select>
        `
        date_el.html(new_html)
        get_routeData(row_index,startDestination,endDestination,date)

    }
}
function get_routeData(row_index,startDestination,endDestination,date){
    if (date==undefined){
        date_el = $("#index-"+row_index).find("#date_select");
        time_el =  $("#index-"+row_index).find("#time_select");
        date=date_el[0].value+ " "+time[0].value
    }
    params={startDestination:startDestination,endDestination:endDestination,date:date,full:false,sortBy:"date"}
    $.ajax({
            url:  window.location.protocol+ "//" +window.location.host +  route_path + "?"+$.param(params),
            type: "get",
            success: function(res) {
                routeData = res.routeData
                routeMap[row_index]=routeData
                date_option_adder(row_index)
            }
    })
}
function date_option_adder(row_index){
    var routeData=routeMap[row_index]
    date = $("#index-"+row_index).find("#date_select");
    time =  $("#index-"+row_index).find("#time_select");
    selected_date=undefined
    if (date[0].value!=undefined && date[0].value!==""){
        selected_date=date[0].value

    }

    else{
        selected_date=routeData[0].date.split(" ")[0]
    }
    date_part  = selected_date.split(" ")[0]
    date[0].innerHTML=""
    time[0].innerHTML=""
    usedDateList=[]
    $(function() {
            $.each(routeData, function(i, item) {
                if (!usedDateList.includes(item.date.split(" ")[0])){
                    usedDateList.push(item.date.split(" ")[0])
                    if (item.date.split(" ")[0]===date_part){
                        var opt_date = $("<option selected>")
                    }
                    else{
                        var opt_date = $("<option>")
                    }
                    opt_date.value=item.date.split(" ")[0]
                    opt_date.text=item.date.split(" ")[0]
                    opt_date.html(item.date.split(" ")[0])
                    date.append(opt_date.clone());
                }

                if (item.date.split(" ")[0]===date_part){
                    var opt_time = $("<option>")
                    opt_time.value=item.date.split(" ")[1]
                    opt_time.text=item.date.split(" ")[1]
                    opt_time.html(item.date.split(" ")[1])
                    time.append(opt_time.clone());
                }

            })
        });

}
function saveUpdate(row_index,id){
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
    var routeData =routeMap[row_index]
    for (i=0;i<routeData.length;i++){
        if (date === routeData[i].date){
            routeId=routeData[i].id
        }
    }
    $.ajax({
            url:window.location.protocol+ "//" +window.location.host +  update_path,
            type: "put",
            contentType:"application/json",
            data: JSON.stringify({routeId:routeId,id:id,state:"POSTPONED",date:date}),
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
    })
}
$(document).ready(function(){
    getDestinationContent()
    dateSynchronizer("startDate")
    dateSynchronizer("endDate",30)
    getPageData()
    create_alert("ticket updated successfully!","error occured while updating ticket!")
})
