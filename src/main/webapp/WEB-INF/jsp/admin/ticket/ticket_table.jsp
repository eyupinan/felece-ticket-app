<table class="table table-striped table-hover">
    <thead>
        <tr>
            <th>Id</th>
            <th>Username</th>
            <th>Start Destination</th>
            <th>End Destination</th>
            <th>Date</th>
            <th>State</th>
            <th>Route Id</th>
            <th style="width:18%">Action</th>
        </tr>
    </thead>
    <tbody id="table_body">
        <tr class="rows" id="index" hidden>
            <td id="id"></td>
            <td id="username"></td>
            <td id="startDestinationName"></td>
            <td id="endDestinationName"></td>
            <td id="date"></td>
            <td id="state">
                <span id="state_span"></span>
            </td>
            <td id="routeId"></td>
            <td>
            <div>
            <button type="button" id="post_button" class="btn btn-warning" >Postpone</button>
            <button type="button" id="cancel_button" class="btn btn-danger">Cancel</button>
            </div>
            </td>
        </tr>
    </tbody>
    <tfoot id="table_foot"></tfoot>
</table>