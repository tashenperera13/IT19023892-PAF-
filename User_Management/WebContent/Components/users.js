$(document).ready(function () {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});



///CLIENT-MODEL================================================================
function validateUserForm() {
	
    // TYPE-------------------------------------
    if ($("#user_Type").val().trim() == "") {
        return "Insert User Type.";
    }
    // NAME-------------------------------------
    if ($("#user_Name").val().trim() == "") {
        return "Insert User Name.";
    }
    // PASSWORD-------------------------------
    if ($("#password").val().trim() == "") {
        return "Insert Password.";
    }
    // EMAIL----------------------------------
    if ($("#user_Email").val().trim() == "") {
        return "Insert Email.";
    }
    // PHONE------------------------------------
    if ($("#user_Phone").val().trim() == "") {
        return "Insert Phone.";
    }
    // ADDRESS------------------------------------
    if ($("#user_Address").val().trim() == "") {
        return "Insert Address.";
    }
    return true;
}



///SAVE-BUTTON================================================================
$(document).on("click", "#btnSave", function (event) 
{
    // Clear alerts
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();
    
    // Form validation
    var status = validateUserForm();
    if (status != true) 
    {
        $("#alertError").text(status);
        $("#alertError").show();
        
        return;
    }
    
    // If valid
    var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT";
    $.ajax(
        {
            url: "UsersAPI",
            type: type,
            data: $("#formUser").serialize(),
            dataType: "text",
            complete: function (response, status) {
            	onUserSaveComplete(response.responseText, status);
            }
        });
});


function onUserSaveComplete(response, status) 
{
    	if (status == "success") 
    	{
    			var resultSet = JSON.parse(response);
    			
    			if (resultSet.status.trim() == "success") 
    			{
    					$("#alertSuccess").text("Successfully saved.");
	    				$("#alertSuccess").show();
	    				
	    				$("#divUsersGrid").html(resultSet.data);
    			} 
    			else if (resultSet.status.trim() == "error") 
    			{
    					$("#alertError").text(resultSet.data);
    					$("#alertError").show();
    			}
    	}
    	
    	else if (status == "error") 
    	{
    			$("#alertError").text("Error while saving.");
    			$("#alertError").show();
    	}	 
    	
    	else 
    	{
    			$("#alertError").text("Unknown error while saving..");
    			$("#alertError").show();
    	}
    	
    	$("#hidUserIDSave").val("");
    	$("#formUser")[0].reset();
}


///UPDATE-BUTTON================================================================
$(document).on("click", ".btnUpdate", function (event) 
{
    	$("#hidUserIDSave").val($(this).data("userid"));
    	$("#user_Type").val($(this).closest("tr").find('td:eq(0)').text());
    	$("#user_Name").val($(this).closest("tr").find('td:eq(1)').text());
    	$("#password").val($(this).closest("tr").find('td:eq(2)').text());
    	$("#user_Email").val($(this).closest("tr").find('td:eq(3)').text());
    	$("#user_Phone").val($(this).closest("tr").find('td:eq(4)').text());
    	$("#user_Address").val($(this).closest("tr").find('td:eq(5)').text());
});


///DELETE-BUTTON================================================================
$(document).on("click", ".btnRemove", function (event) 
{
    $.ajax(
        {
            url: "UsersAPI",
            type: "DELETE",
            data: "user_Id=" + $(this).data("userid"),
            dataType: "text",
            complete: function (response, status) 
            {
            	onUserDeleteComplete(response.responseText, status);
            }
        });
});


function onUserDeleteComplete(response, status) 
{
    	if (status == "success") 
    	{
    			var resultSet = JSON.parse(response);
    			
    			if (resultSet.status.trim() == "success") 
    			{
    					$("#alertSuccess").text("Successfully deleted.");
    					$("#alertSuccess").show();
    					
    					$("#divUsersGrid").html(resultSet.data);
    			}
    			
    			else if (resultSet.status.trim() == "error")
    			{
    					$("#alertError").text(resultSet.data);
    					$("#alertError").show();
    			}
    	} 
    	
    	else if (status == "error") 
    	{
    			$("#alertError").text("Error while deleting.");
    			$("#alertError").show();
    	} 
    	
    	else 
    	{
    			$("#alertError").text("Unknown error while deleting..");
    			$("#alertError").show();
    	}
}