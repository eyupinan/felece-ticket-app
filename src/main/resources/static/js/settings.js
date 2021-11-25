function getDestinationContent(){
    $.ajax({
            url:  window.location.protocol+ "//" +window.location.host + destination_path,
            type: "get",
            success: function(res) {
                fill_destination_table(res)
            }
    })
}

function getVehicleContent(){
    $.ajax({
            url:  window.location.protocol+ "//" +window.location.host +  vehicle_path,
            type: "get",
            success: function(res) {
                fill_vehicle_table(res)
            }
    })
}

function fill_destination_table(res){
    $(".rows").attr("hidden", true)
    $(".rows").attr("hidden", true)
    $(function() {
                    $.each(res, function(i, item) {
                        if (!$("#destination_row-" + i).length) {
                            row = $("#destination_row").clone().attr("id", "destination_row-" + i).removeAttr('hidden').appendTo("#destination_table_body");
                        } else {
                            row = $("#destination_row-" + i).removeAttr("hidden")
                        }
                        row.find("#destination_id").text(item.id)
                        row.find("#destination_name").text(item.name)


                    });
                });
}
function fill_vehicle_table(res){

    $(".rows").attr("hidden", true)
    $(function() {
                    $.each(res, function(i, item) {
                        if (!$("#vehicle_row-" + i).length) {
                            row = $("#vehicle_row").clone().attr("id", "vehicle_row-" + i).removeAttr('hidden').appendTo("#vehicle_table_body");
                        } else {
                            row = $("#vehicle_row-" + i).removeAttr("hidden")
                        }
                        row.find("#vehicle_id").text(item.id)
                        row.find("#vehicle_plate").text(item.plate)
                        row.find("#vehicle_capacity").text(item.totalSeatCount)


                    });
                });

}
function openTab(evt, tabName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tab_content");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(tabName).style.display = "block";
    if (tabName=="Destination"){
        getDestinationContent()
    }
    if (tabName=="Vehicle"){
            getVehicleContent()
        }
    evt.className += " active";
}
function onSubmit(form,route="destination",method="post"){
    console.log(route)
    url=  window.location.protocol+ "//" +window.location.host + api_path +"/"+route
    name=""
    data={}
    for (let i = 0; i < form.children.length; i++) {
      if (form.children[i].name.trim()==="name"){
        data.name=form.children[i].value
      }
      if (form.children[i].name.trim()==="id"){
              data.id=form.children[i].value
              if (method==="delete"){
                          url=url+"?id="+data.id
              }
      }
      if (form.children[i].name.trim()==="plate"){
              data.plate=form.children[i].value
      }
      if (form.children[i].name.trim()==="totalSeatCount"){
                    data.totalSeatCount=form.children[i].value
      }
    }
    $.ajax({
                url: url,
                type: method,
                contentType : 'application/json',
                data: JSON.stringify(data),
                success: function(res) {

                    getDestinationContent()
                    getVehicleContent()
                    if (method==="post" || method==="delete" || method==="put"){
                        var current_url = window.location;
                       var url = new URL(current_url);
                       url.searchParams.set('err', "false");
                       window.location.href=url
                    }

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
    openTab(document.getElementById("contact-tab-destination"), "Destination")
    getDestinationContent()
    create_alert("Proccess successfull!","Error occured!")
})


