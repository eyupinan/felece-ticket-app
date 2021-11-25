<html>
    <head>
    <script src="/js/jQuery-3.5.1.js"></script>
    <link rel="stylesheet" href="/css/basic.css">
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="/css/adminlte.css">
    <link rel="stylesheet" href="/css/list.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
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
           leftbar_url =  window.location.protocol+ "//" +window.location.host +  "${leftbar_path}"
               $(function() {
                   $("#leftbar").load(leftbar_url);
               });
           </script>
        <section class="page-content">
        <p id="msg" hidden></p>
                <article id="art" style="width:60%;" class="center">
                    <form onsubmit="return=false;" method="POST" accept-charset="utf-8">
                      <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" name="username">
                      </div>
                      <div class="form-group">
                        <label for="phone">Phone</label>
                        <input type="text" class="form-control" id="phone" name="phone">
                      </div>
                      <div class="form-group">
                        <label for="address">Address</label>
                        <input type="text" class="form-control" id="address" name="address">
                      </div>
                      <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email">
                      </div>
                        <div class="form-group">
                          <label for="password">Password</label>
                          <input type="password" class="form-control" id="password" name="password">
                        </div>
                      <div class="form-group">
                        <label for="role">Role</label>
                        <select id ="role" class="form-control" name="role">
                            <option value="ROLE_ADMIN">ADMIN</option>
                            <option value="ROLE_USER" selected>USER</option>
                        </select>
                      </div>
                      <button type="button" onclick="onSubmit()" class="btn btn-primary">Submit</button>
                    </form>
				</article>
		</section>
		<script>
		user_path = "${user_path}"
		user_post_path = "${user_post_path}"
		</script>
		<script src="/js/user_create.js"></script>
		<script src="/js/alert.js"></script>
	</body>
</html>