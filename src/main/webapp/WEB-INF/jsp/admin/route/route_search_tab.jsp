<label>Search: <input type="search" class="search" id="search_by_id" placeholder="search by id">
</label><br>
<label> From :
    <select name="start_destinations" id="select_start_destinations">
    </select>
    To :
    <select name="end_destinations" id="select_end_destinations">
    </select>
    Date:
        <input type="date" id="startDate" name="startDate">
</label>
<button type="button" class="btn btn-primary" onClick="onSearch()">Search</button>
<a class="btn btn-primary" href="http://localhost:9001/admin/route/create">Create New</a>