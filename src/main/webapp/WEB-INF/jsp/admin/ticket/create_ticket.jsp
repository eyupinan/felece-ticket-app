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
                    $(function() {
                        $("#leftbar").load("${leftbar_url}");
                    });
                </script>
        <section class="page-content">
        <p id="msg" hidden></p>
                <article id="art" style="width:60%;" class="center">
                    <form action="../../admin/api/ticket" method="POST" accept-charset="utf-8">
                      <div class="form-group">
                        <label for="select_start_destinations">Start Destination Name</label>
                        <select class="form-control" id="select_start_destinations" onchange="get_routeData()">
                        </select>
                      </div>

                      <div class="form-group">
                        <label for="select_end_destinations">End Destination Name</label>
                        <select class="form-control" id="select_end_destinations" onchange="get_routeData()">
                        </select>
                      </div>
                      <div class="form-group">
                        <label for="date">Date</label>
                        <select id="date_select" class="form-control" onchange="date_option_adder()"></select>
                        <select id="time_select" class="form-control" onchange="calculate_new_routeId()"></select>
                      </div>
                      <div class="form-group">
                          <label for="routeId">Route Id</label>
                        <input type="text" name="routeId" class="form-control" id="routeId" />
                        </div>
                      </div>
                        <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" name="userName">
                        </div>
                      <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
				</article>
		</section>
		<script>
		destination_url="${destination_url}"
		route_url="${route_url}"
		</script>
		<script src="/js/create_ticket.js"></script>
		<script src="/js/alert.js"></script>
	</body>
</html>