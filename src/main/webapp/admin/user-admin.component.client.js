(function(){
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld;
    var $roleFld;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    $(main);

    function main() {
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $roleFld = $("#roleFld");
        $userRowTemplate = $(".wbdv-template");
        $tbody = $("tbody");
        userService
            .findAllUsers()
            .then(renderUsers);
        $(".wbdv-create").click(function() {
        	createUser();
        });
        $(".wbdv-edit").click(function(event) {
        	selectUser(event);
        });
        $(".wbdv-update").click(function(event) {
        	updateUser(event);
        });
        $(".wbdv-remove").click(function(event) {
        	deleteUser(event);
        });
        $(".wbdv-search").click(function(event) {
        	searchUser();
        });
    }
    
    function searchUser(){
    	var userObj = new User($usernameFld.val(),
				   $passwordFld.val(),
				   $firstNameFld.val(),
				   $lastNameFld.val(),
				   $roleFld.val());
    	userService
    	.searchUser(userObj).then(renderSearchedUsers);
    }
    
    function createUser() {
    	if(!checkForm()){
    		return
    	}
    	var userObj = new User($usernameFld.val(),
				   $passwordFld.val(),
				   $firstNameFld.val(),
				   $lastNameFld.val(),
				   $roleFld.val());
    	userService
        .createUser(userObj).then(function(user){
        	if(user.id == null){
        		alert("username already taken, please select a different username!")
        		return;
        	}
        	$("#template-row").show();
        	$userRowTemplate = $("#template-row");
        	var clone = $userRowTemplate.clone(true,true);
        	clone.find(".wbdv-username").html(user.username);
            clone.find(".wbdv-first-name").html(user.firstName);
            clone.find(".wbdv-last-name").html(user.lastName);
            clone.find(".wbdv-role").html(user.role);
            clone.attr("id", "row"+user.id);
            clone.find(".wbdv-edit").attr("id", "edit"+user.id);
            clone.find(".wbdv-remove").attr("id", "rmv"+user.id);
            $tbody.append(clone);
            $("#template-row").hide();
        });
    	resetForm();
    }
    function findAllUsers() {  }
    function findUserById(id) {
    	
    }
    function deleteUser(event) {
    	var id = $(event.currentTarget).attr('id').substring(3);
    	userService.deleteUser(id)
    	.then(function(){
    		$('#row'+id).remove();
    	});
    }
    function selectUser(event) {
    	var clone = $(event.currentTarget).parent().closest('tr');
    	$usernameFld.val(clone.find(".wbdv-username").html());
    	$passwordFld.val(clone.find(".wbdv-password").html());
    	$firstNameFld.val(clone.find(".wbdv-first-name").html());
    	$lastNameFld.val(clone.find(".wbdv-last-name").html());
    	
    	$(".wbdv-update").attr("id",$(event.currentTarget).attr('id').substring(4));
    	//clone.remove();
    }
    function updateUser(event) {
    	if(!checkForm()){
    		return
    	}
    	var userObj = new User($usernameFld.val(),
    						   $passwordFld.val(),
    						   $firstNameFld.val(),
    						   $lastNameFld.val(),
    						   $roleFld.val());
    	userService.updateUser($(event.currentTarget).attr('id'), userObj)
    	.then(renderUser)
    }
    function renderUser(userObj) {
    	var clone = $('#row'+userObj.id);
    	clone.find(".wbdv-username").html(userObj.username);
        clone.find(".wbdv-first-name").html(userObj.firstName);
        clone.find(".wbdv-last-name").html(userObj.lastName);
        clone.find(".wbdv-role").html(userObj.role);
        $(".wbdv-update").attr("id","wbdv-update");
        resetForm();
    }
    function renderUsers(users) {
    	$('[id="row"]').remove();
    	for(var u=0; u<users.length; u++) {
            var userObj = new User(users[u].username, 
            					   users[u].password,
            					   users[u].firstName,
            					   users[u].lastName,
            					   users[u].role);
            var clone = $userRowTemplate.clone(true,true);
            clone.find(".wbdv-username").html(userObj.username);
            clone.find(".wbdv-first-name").html(userObj.firstName);
            clone.find(".wbdv-last-name").html(userObj.lastName);
            clone.find(".wbdv-role").html(userObj.role);
            clone.attr("id", "row"+users[u].id);
            clone.find(".wbdv-edit").attr("id", "edit"+users[u].id);
            clone.find(".wbdv-remove").attr("id", "rmv"+users[u].id);
            $tbody.append(clone);
        }
    	$("#template-row").hide();
    }
    
    function renderSearchedUsers(users) {
    	if(users.length == 0){
    		alert("No user(s) found!")
    		return;
    	}
    	$('#wbdv-userTable > tbody  > tr').each(function() {
    		var id = $(this).attr("id");
    		if(id.startsWith("row") && $(this).is(":visible")){
    			$(this).remove();
    		}
    	});
    	$("#template-row").show();
    	for(var u=0; u<users.length; u++) {
            var userObj = new User(users[u].username, 
            					   users[u].password,
            					   users[u].firstName,
            					   users[u].lastName,
            					   users[u].role);
            var clone = $userRowTemplate.clone(true,true);
            clone.find(".wbdv-username").html(userObj.username);
            clone.find(".wbdv-first-name").html(userObj.firstName);
            clone.find(".wbdv-last-name").html(userObj.lastName);
            clone.find(".wbdv-role").html(userObj.role);
            clone.attr("id", "row"+users[u].id);
            clone.find(".wbdv-edit").attr("id", "edit"+users[u].id);
            clone.find(".wbdv-remove").attr("id", "rmv"+users[u].id);
            $tbody.append(clone);
        }
    	$("#template-row").hide();
    	resetForm();
    }
    
    function resetForm() {
    	$usernameFld.val('');
    	$passwordFld.val('');
		$firstNameFld.val('');
		$lastNameFld.val('');
		$roleFld.val('');
    }
    
    function checkForm() {
    	var flag = false;
    	if($usernameFld.val() == ""){
    		flag = true;
    	}
    	if($passwordFld.val() == ""){
    		flag = true;
    	}
    	if($firstNameFld.val() == ""){
    		flag = true;
    	}
    	if($lastNameFld.val() == ""){
    		flag = true;
    	}
    	if($roleFld.val() == ""){
    		flag = true;
    	}
    	if (flag == true){
    		alert("All the fields are mandatory!")
    		return false;
    	}
    	else{
    		return true;
    	}
    }
    
})();