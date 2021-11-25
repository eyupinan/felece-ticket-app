function action(id){
    if (userName==null || userName===""){
        location.href="/login"
    }
    body ={}
    body.userName=userName
    body.routeId=id
    $.ajax({
        url: window.location.protocol+ "//" +window.location.host + reservation_path,
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
function url_generator(){
    query_obj={}
    startDest=$("#select_start_destinations")[0].value
    console.log($("#select_start_destinations"))
    endDest=$("#select_end_destinations")[0].value
    startDate=$("#startDate")[0].value
    limit=10
    query_obj.sortBy="date"
    query_obj.limit=limit
    query_obj.offset=limit* (pageIndex-1)
	if (startDest!=undefined && startDest!=="empty" && startDest!==""){
		query_obj.startDestination=startDest
	}
	if (endDest!=undefined && endDest!=="empty" && endDest!==""){
		query_obj.endDestination=endDest
	}
	if (startDate!=undefined){
		query_obj.date=startDate
	}
    var str = $.param( query_obj );
    if (str!==""){
        url = window.location.protocol+ "//" +window.location.host + route_path+"?"+str
    }
    return url
}
function getPageData(){
    url = url_generator()
     $.ajax({
            url: url,
            type: "get",
            success: function(res) {
                routeData = res.routeData
                pageCount = res.pageCount
                $(".rows").attr("hidden", true)
                $(function() {
                    $.each(routeData, function(i, item) {

                        if (!$("#index-" + i).length) {
                            row = $("#index").clone().attr("id", "index-" + i).removeAttr('hidden').appendTo("#table_body");
                        } else {
                            row = $("#index-" + i).removeAttr("hidden")
                        }

                        row.find("#id").text(item.id)
                        row.find("#startDestinationName").text(item.startDestinationName)
                        row.find("#endDestinationName").text(item.endDestinationName)
                        row.find("#date").text(item.date)
                        row.find("#isFull").text(item.full)
                        row.find("#vehiclePlate").text(item.vehiclePlate)
                        row.find("#action").html("")
                        row.find("#action").append($(`<button type="button" style="color:white" class="btn btn-primary"
                            onclick="action(${item.id})"
                         ${item.full ? "disabled": ""}>${action_button_label}</button>`))



                    });
                });
                create_page_buttons(pageIndex,pageCount)

            }
        });
}
$(document).ready(function(){
    create_alert("Reservation successfull!","Error occured while creating reservation")
})