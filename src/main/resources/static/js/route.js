pageCount=1
pageIndex=1
function getDestinationContent(){
    console.log("destination url :",destination_url)
    $.ajax({
            url: destination_url,
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
function dateSynchronizer(){
    date_el=$("#startDate")[0]
    current_date = new Date()
    var month=current_date.getMonth()+1
    date =current_date.getFullYear().toString()+"-"+month.toString()+"-"+current_date.getDate().toString()
    date_el.value=date
    date_el.min=date

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

$(document).ready(function(){
    getDestinationContent()
    dateSynchronizer()
    getPageData()
    create_alert()
})
