<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
    <script src="/js/jQuery-3.5.1.js"></script>
    <link rel="stylesheet" href="/css/basic.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/datatable.css">
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="/css/adminlte.css">
    <link rel="stylesheet" href="/css/list.css">
    </head>
    <body>
        <div id="leftbar"></div>
        <script>
            leftbar_url =  window.location.protocol+ "//" +window.location.host + "${leftbar_path}"
                    $(function() {
                        $("#leftbar").load(leftbar_url);
                    });
        </script>
        <section class="page-content">
            <p id="msg" hidden></p>
            <section class="grid">

                <article id="art">
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th style="width:35%">Column</th>
                                    <th style="width:55%">Value</th>
                                    <th style="width:10%">Action</th>
                                </tr>
                            </thead>
							<tbody id="table_body">
                                    <tr class="rows">
                                        <td>Id</td>
                                        <td>${id}</td>
                                        <td></td>
                                    </tr>
									<tr class="rows">
                                        <td>Start Destination Id</td>
                                        <td><div id="startDestinationId">${data.startDestinationId}</div>
										<input type="text" class="search" id="startDestinationId_input" placeholder="${data.startDestinationId}" hidden /></td>
                                        <td><button type="button" class="btn btn-primary" onClick="action(this,'startDestinationId')" >
                                        Update</button>
                                        
                                        </td>
                                    </tr>
                                    <tr class="rows">
                                        <td>End Destination Id</td>
                                        <td><div id="endDestinationId">${data.endDestinationId}</div>
										<input type="text" class="search" id="endDestinationId_input" placeholder="${data.endDestinationId}" hidden /></td>
                                        <td><button type="button" class="btn btn-primary" onClick="action(this,'endDestinationId')">
                                                                                Update</button>
                                        
                                        </td>
                                    </tr>
                                    <tr class="rows">
                                        <td>Start Destination Name</td>
                                        <td><div id="startDestinationName">${data.startDestinationName}</div>
										<select name="start_destinations" id="startDestinationName_input" hidden>
										</select></td>
                                        <td><button type="button" class="btn btn-primary" onClick="action(this,'startDestinationName','${data.startDestinationName}')">
                                                                                Update</button>
                                        </td>
                                    </tr>
                                    <tr class="rows" id="index">
                                        <td>End Destination Name</td>
                                        <td><div id="endDestinationName">${data.endDestinationName}</div>
										<select name="end_destinations" id="endDestinationName_input" hidden>
											</select></td></td>
                                        <td><button type="button" class="btn btn-primary" onClick="action(this,'endDestinationName','${data.endDestinationName}')">
                                                                                Update</button>
											
                                    </tr>
                                    <tr class="rows" id="index">
                                        <td>date</td>
                                        <td><div id="date">${data.date}</div>
										<input type="date" class="date_input" hidden />
										<input type="time" class="date_input" hidden /></td>
                                        <td><button type="button" class="btn btn-primary" onClick="action(this,'date','${data.date}')">
                                                                                Update</button>
											</td>
                                    </tr>
                                    <tr class="rows" id="index">
                                        <td>price</td>
                                        <td><div id="price">${data.price}</div>
										<input type="text" class="search" id="price_input" placeholder="${data.price}" hidden /></td>
                                        <td><button type="button" class="btn btn-primary" onClick="action(this,'price')">
                                                                                Update</button>
											</td>
                                    </tr>
                                    <tr class="rows" id="index">
                                        <td>Is Full</td>
                                        <td>${data.full}</td>
                                        <td></td>
                                    </tr>
                                    <tr class="rows" id="index">
                                        <td>Vehicle Id</td>
                                        <td><div id ="vehicleId">${data.vehicleId}</div>
										<input type="text" class="search" id="vehicleId_input" placeholder="${data.vehicleId}" hidden /></td>
                                        <td><button type="button" class="btn btn-primary" onClick="action(this,'vehicleId')">
                                                                                Update</button>
											</td>
                                    </tr>
                                    <tr class="rows" id="index">
                                        <td>Vehicle Plate</td>
                                        <td><div id ="vehiclePlate">${data.vehiclePlate}</div>
										<input type="text" class="search" id="vehiclePlate_input" placeholder="${data.vehiclePlate}" hidden /></td>
                                        <td><button type="button" class="btn btn-primary" onClick="action(this,'vehiclePlate')" >
                                                                                Update</button>
											</td>
                                    </tr>
                                    <tr class="rows" id="index">
                                        <td>Created At</td>
                                        <td>${createdAt}</td>
                                        <td></td>
                                    </tr>

                                    <tr class="rows" id="index">
                                        <td>Updated At</td>
                                        <td>${updatedAt}</td>
                                        <td></td>
                                    </tr>



                            </tbody>
                            <tfoot id="table_foot"></tfoot>
                        </table>

                </article>
                <div>
                        <button type="button" class="btn btn-primary" onClick="deleteRequest(${id})">Disable Route</button>
                        </div>
            </section>
        </section>
     <script>
     destination_path="${destination_path}"
     update_path="${update_path}"
     routeId=${id}
     route_after_disable = "${route_after_disable}"
     </script>
     <script src="/js/alert.js"></script>
     <script src="/js/route_details.js"></script>

    </body>
</html>