pageCount=1
pageIndex=1
function url_generator(){

    query_obj={}
    username=$("#search_by_username")[0].value
    byId=parseInt($("#search_by_id")[0].value)
    limit=15
    query_obj.sortBy="username"
    query_obj.limit=limit
    query_obj.offset=limit* (pageIndex-1)
    if (username!=null && username!=""){
		query_obj.username=username
	}
	if (byId!=NaN && byId!="" && !isNaN(byId)){
    		query_obj.id=byId
    	}
    console.log(query_obj)
    var str = $.param( query_obj );
    console.log(str)
    if (str!==""){
        url = base_url+"?"+str
    }
    return url
}
function getPageData(){
    url = url_generator()
    console.log(url)
     $.ajax({
            url: url,
            type: "get",
            success: function(res) {
                userData = res.userData
                pageCount = res.pageCount
                console.log(userData)
                $(".rows").attr("hidden", true)
                $(function() {
                    $.each(userData, function(i, item) {
                        if (!$("#index-" + i).length) {
                            row = $("#index").clone().attr("id", "index-" + i).removeAttr('hidden').appendTo("#table_body");
                        } else {
                            row = $("#index-" + i).removeAttr("hidden")
                        }

                        row.find("#id").text(item.id)
                        row.find("#username").text(item.username)
                        row.find("#phone").text(item.phone)
                        row.find("#address").text(item.address)
                        row.find("#email").text(item.email)
                        row.find("#role").html(item.role)
                        row.find("#action").html("")
                        row.find("#action").append($(`<a style="color:white" id="details_button" class="btn btn-primary" href="/admin/users/${item.id}">Details</a>`))

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
function onSearch(){
    getPageData()
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
$(document).ready(function(){
    getPageData()
    create_alert("proccess successfull!","Error occured while creating or updating user")
})
