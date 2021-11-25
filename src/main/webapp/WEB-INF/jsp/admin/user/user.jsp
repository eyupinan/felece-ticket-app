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
           leftbar_url =  window.location.protocol+ "//" +window.location.host +  "${leftbar_path}"
               $(function() {
                   $("#leftbar").load(leftbar_url);
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
                            <label>Search By Id: <input type="search" class="search" id="search_by_id" placeholder="search by id">
                                 Search By Username: <input type="search" class="search" id="search_by_username" placeholder="search by username">
                            </label>
                            <button type="button" class="btn btn-primary" onClick="onSearch()">Search</button>
                            <a class="btn btn-primary" href="${user_create_path}">Create New</a>
                         </div>
                         <div id="page_buttons"></div>
						 <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Username</th>
                                    <th>Phone</th>
                                    <th>Address</th>
                                    <th>Email</th>
                                    <th>Role</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
							<tbody id="table_body">
                                <tr class="rows" id="index" hidden>
                                    <td id="id"></td>
                                    <td id="username"></td>
                                    <td id="phone"></td>
                                    <td id="address"></td>
                                    <td id="email"></td>
                                    <td id="role"></td>
                                    <td id="action">``</td>
                                </tr>
                            </tbody>
                            <tfoot id="table_foot"></tfoot>
                        </table>
					</div>
				</article>
			</section>
		</section>
		<script>
		    user_path="${user_path}"
		</script>
		<script src="/js/user.js"></script>
		<script src="/js/alert.js"></script>
	</body>
</html>