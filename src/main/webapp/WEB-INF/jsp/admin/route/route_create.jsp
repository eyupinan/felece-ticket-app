<html>
    <head>
    <script src="/js/jQuery-3.5.1.js"></script>
    <link rel="stylesheet" href="/css/basic.css">
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="/css/adminlte.css">
    <link rel="stylesheet" href="/css/list.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/styles.css">
    <style>
        .center {
          margin: auto;
          width: 50%;
          padding: 10px;
        }
    </style>
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
                <article id="art" style="width:60%;" class="center">
                    <form onsubmit="return false;">
                      <div class="form-group">
                        <label for="select_start_destinations">Start Destination Name</label>
                        <select name="start_destinations" class="form-control" id="select_start_destinations">
                        </select>
                      </div>

                      <div class="form-group">
                        <label for="select_end_destinations">End Destination Name</label>
                        <select name="end_destinations" class="form-control" id="select_end_destinations">
                        </select>
                      </div>
                      <div class="form-group">
                        <label for="startDate">Date</label>
                        <input type="date" id="startDate" class="form-control">
                        <input type="time" id="startTime" class="form-control">
                      </div>
                        <div class="form-group">
                        <label for="price">Price</label>
                        <input type="text" class="form-control" id="price">
                        <div class="form-group">
                        <label for="vehiclePlate">vehiclePlate</label>
                        <select name="vehiclePlate" class="form-control" id="vehiclePlate">
                                                </select>
                      </div>
                      <button type="button" class="btn btn-primary" onclick="onSubmit()">Submit</button>
                    </form>
				</article>
		</section>
		<script>
		    post_path = "${post_path}"
		    destination_path = "${destination_path}"
		    vehicle_path = "${vehicle_path}"
		</script>
		<script src="/js/route_create.js"></script>
		<script src="/js/alert.js"></script>
	</body>
</html>