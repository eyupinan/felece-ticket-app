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
                    $(function() {
                        $("#leftbar").load("${leftbar_url}");
                    });
                </script>
        <section class="page-content">
            <section class="grid">
                <p id="msg" hidden></p>
                <article id="art">

                    <div class="table-wrapper overflow-x-auto">

                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-6">
                                    <h2>Routes</h2>
                                </div>
                            </div>
                        </div>
						<div>
                            <jsp:include page="${search_tab}" />
                         </div>
                         <div id="page_buttons"></div>
                         <jsp:include page="${table}" />
					</div>
				</article>
			</section>
		</section>
		<script>
		    action_button_label="${action_button_label}"
		    userName="${userName}"
            base_url = "${base_url}"
            destination_url = "${destination_url}"
            reservation_url =  "${reservation_url}"
        </script>
        <script src="/js/${external_js}"></script>
		<script src="/js/route.js"></script>
		<script src="/js/alert.js"></script>
	</body>
</html>