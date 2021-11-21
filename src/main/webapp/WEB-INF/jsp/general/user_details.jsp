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
                    $(function() {
                        $("#leftbar").load("${leftbar_url}");
                    });
        </script>
        <section class="page-content">
        <div><p id="msg" hidden></p></div>
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
								<!--<c:forEach var = "index" begin = "1" end = "5">
									<tr class="rows">
                                        <td>${columnList[index]}</td>
                                        <td>${valueList[index]}</td>
                                        <td><c:if test="${actionButtonBool[index]}">
										<button type="button" class="btn btn-primary" onClick="action('${idList[index]}')" id="${idList[index]}">
                                        Update</button>
										<input type="text" class="search" id="${idList[index]}_input" placeholder="${valueList[index]}" hidden>
										<input type="date" class="search" id="${idList[index]}_date"  hidden>
										</c:if></td>
                                    </tr>
								</c:forEach>-->
                                    <tr class="rows">
                                        <td>Id</td>
                                        <td>${data.id}</td>
                                        <td></td>
                                    </tr>
									<tr class="rows">
                                        <td>User Name</td>
                                        <td><div id="username">${data.username}</div>
										<input type="text" class="search" id="username_input" placeholder="${data.username}" hidden /></td>
                                        <td><button type="button" class="btn btn-primary" onClick="action(this,'username')" >
                                        Update</button>

                                        </td>
                                    </tr>
                                    <tr class="rows">
                                        <td>Phone</td>
                                        <td><div id="phone">${data.phone}</div>
										<input type="text" class="search" id="phone_input" placeholder="${data.phone}" hidden /></td>
                                        <td><button type="button" class="btn btn-primary" onClick="action(this,'phone')">
                                                                                Update</button>

                                        </td>
                                    </tr>
                                    <tr class="rows">
                                        <td>Address</td>
                                        <td><div id="address">${data.address}</div>
										<input type="text" name="address" id="address_input" placeholder="${data.address}" hidden />
										</td>
                                        <td><button type="button" class="btn btn-primary" onClick="action(this,'address')">
                                                                                Update</button>
                                        </td>
                                    </tr>
                                    <tr class="rows" id="index">
                                        <td>Email</td>
                                        <td><div id="email">${data.email}</div>
										<input type="email" name="email" id="email_input" placeholder="${data.email}" hidden />
											</td></td>
                                        <td><button type="button" class="btn btn-primary" onClick="action(this,'email')">
                                                                                Update</button>

                                    </tr>
                                    <tr class="rows" id="index">
                                        <td>Password</td>
                                        <td><div id="password"></div>
                                        <input type="password" name="email" id="password_input" hidden />
                                            </td></td>
                                        <td><button type="button" class="btn btn-primary" onClick="action(this,'password')">
                                                                                Update</button>

                                    </tr>
                                    <tr class="rows" id="index">
                                        <td>Role</td>
                                        <td>${data.role}</td>
                                        <td></td>
                                    </tr>
                                    <tr class="rows" id="index">
                                        <td>Created At</td>
                                        <td>${data.createdAt}</td>
                                        <td></td>
                                    </tr>

                                    <tr class="rows" id="index">
                                        <td>Updated At</td>
                                        <td>${data.updatedAt}</td>
                                        <td></td>
                                    </tr>



                            </tbody>
                            <tfoot id="table_foot"></tfoot>
                        </table>

                </article>
                <div>
                        <button type="button" class="btn btn-primary" onClick="deleteRequest(${id})">Delete User</button>
                        </div>
            </section>
        </section>
     <script>
        userId=${data.id}
        update_url = "${update_url}"
     </script>
     <script src="/js/alert.js"></script>
     <script src="/js/user_details.js"></script>

    </body>
</html>``