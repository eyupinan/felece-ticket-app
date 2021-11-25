<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                leftbar_url = window.location.protocol+ "//" +window.location.host + "${leftbar_path}"
                        $(function() {
                            $("#leftbar").load(leftbar_url);
                        });
                    </script>
        <section class="page-content">
            <p id="msg" hidden></p>
            <article style="resize: both;position: relative;resize: both;
                overflow: auto;" id="notif">
                <br>
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-12 col-lg-10 col-xl-8 mx-auto">
                            <h2 class="h3 mb-4 page-title">Settings</h2>
                            <div class="my-4">
                                <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active tablinks" id="contact-tab-destination" data-toggle="tab" onclick="openTab(this, 'Destination')" href="#" role="tab" aria-controls="contact" aria-selected="false">Destination</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link tablinks" id="contact-tab-vehicle" data-toggle="tab" onclick="openTab(this, 'Vehicle')" href="#" role="tab" aria-controls="contact" aria-selected="false">Vehicle</a>
                                    </li>
                                </ul>
                                <div class="tab_content" id="Destination">
                                    <p>All Destinations</p>
									 <table class="table table-striped table-hover">
										<thead>
											<tr>
												<th>Id</th>
												<th>Destination Name</th>
											</tr>
										</thead>
										<tbody id="destination_table_body">
											<tr class="rows destination_rows" id="destination_row" hidden>
												<td id="destination_id"></td>
												<td id="destination_name"></td>
											</tr>
										</tbody>
									</table>
									<hr class="my-4" />
									<p>Add New Destination</p>
									<form onsubmit="onSubmit(this,'destination','post');return false">
									    <input type="text" name="name" placeholder="destination name"/>
									    <input type="submit" value="create"/>
									</form>
                                    <hr class="my-4" />
									<p>Update Destination</p>
									<form onsubmit="onSubmit(this,'destination','put');return false">
									<input type="text" name="id" placeholder="destination id"/>
									    <input type="text" name="name" placeholder="destination name"/>
									    <input type="submit" value="update"/>
									</form>
                                    <hr class="my-4" />
									<p>Delete Destination</p>
									<form onsubmit="onSubmit(this,'destination','delete');return false">
									    <input type="text" name="id" placeholder="destination id"/>
									    <input type="submit" value="delete"/>
									</form>

                                </div>
                                <div class="tab_content" id="Vehicle">
									<table class="table table-striped table-hover">
										<thead>
											<tr>
												<th>Id</th>
												<th>Vehicle Plate</th>
												<th>Vehicle Capacity</th>
											</tr>
										</thead>
										<tbody id="vehicle_table_body">
											<tr class="rows vehicle_rows" id="vehicle_row" hidden>
												<td id="vehicle_id"></td>
												<td id="vehicle_plate"></td>
												<td id="vehicle_capacity"></td>
											</tr>
										</tbody>
									</table>
                                    <hr class="my-4" />
									<p>Add New Vehicle</p>
									<form onsubmit="onSubmit(this,'vehicle','post');return false">
									    <input type="text" name="plate" placeholder="vehicle Plate"/>
									    <input type="text" name="totalSeatCount" placeholder="vehicle capacity"/>
									    <input type="submit" value="create"/>
									</form>
                                    <hr class="my-4" />
									<p>Update Vehicle</p>
									<form onsubmit="onSubmit(this,'vehicle','put');return false">
									    <input type="text" name="id" placeholder="vehicle id"/>
									    <input type="text" name="plate" placeholder="vehicle plate"/>
									    <input type="text" name="totalSeatCount" placeholder="vehicle capacity"/>
									    <input type="submit" value="update"/>
									</form>
                                    <hr class="my-4" />
									<p>Delete Vehicle</p>
									<form onsubmit="onSubmit(this,'vehicle','delete');return false">
									    <input type="text" name="id" placeholder="vehicle id"/>
									    <input type="submit" value="delete"/>
									</form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </article>
		<section>
		<script>
		api_path = "/admin/api"
		destination_path="${destination_path}"
		vehicle_path="${vehicle_path}"
		</script>
		<script src="/js/settings.js"></script>
		<script src="/js/alert.js"></script>
    </body>
</html>