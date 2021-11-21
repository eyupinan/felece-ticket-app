<label> From :
    <select name="start_destinations" id="select_start_destinations">
    </select>
    To :
    <select name="end_destinations" id="select_end_destinations">
    </select>
    State :
    <select name="state" id="state">
        <option value="empty">Select State</option>
        <option value="RECEIPT">RECEIPT</option>
        <option value="POSTPONED">POSTPONED</option>
        <option value="CANCELED">CANCELED</option>
    </select>
    Start Date:
        <input type="date" id="startDate" name="startDate">
    End Date:
        <input type="date" id="endDate" name="endDate">
</label>
<button type="button" class="btn btn-primary" onClick="onSearch()">Search</button>
<a  class="btn btn-primary" href="/routes">Create New</a>