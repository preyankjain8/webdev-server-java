(function(){
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    $(main);

    function main() {
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
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
    }
    function createUser() {
    	var userObj = new User($usernameFld.val(),
				   $passwordFld.val(),
				   $firstNameFld.val(),
				   $lastNameFld.val());
    	var clone = $userRowTemplate.clone(true, true);
        clone.find(".wbdv-username").html(userObj.username);
        clone.find(".wbdv-first-name").html(userObj.firstName);
        clone.find(".wbdv-last-name").html(userObj.lastName);
        $tbody.append(clone);
        userService
        .createUser(userObj).then(function(user){
        	clone.find(".wbdv-edit").attr("id",user.id);
        });
    }
    function findAllUsers() {  }
    function findUserById(id) {
    	
    }
    function deleteUser() {  }
    function selectUser(event) {
    	var clone = $(event.currentTarget).parent().closest('tr');
    	$usernameFld.val(clone.find(".wbdv-username").html());
    	$passwordFld.val(clone.find(".wbdv-password").html());
    	$firstNameFld.val(clone.find(".wbdv-first-name").html());
    	$lastNameFld.val(clone.find(".wbdv-last-name").html());
    	$(".wbdv-update").attr("id",$(event.currentTarget).attr('id'));
    	//clone.remove();
    }
    function updateUser(event) {
    	var userObj = new User($usernameFld.val(),
    						   $passwordFld.val(),
    						   $firstNameFld.val(),
    						   $lastNameFld.val());
    	userService.updateUser($(event.currentTarget).attr('id'), userObj)
    	.then(renderUser)
    }
    function renderUser(userObj) {
    	var clone = $('#row'+userObj.id);
    	clone. find(".wbdv-username").html(userObj.username);
        clone.find(".wbdv-first-name").html(userObj.firstName);
        clone.find(".wbdv-last-name").html(userObj.lastName);
        $tbody.append(clone);
    }
    function renderUsers(users) {
    	for(var u=0; u<users.length; u++) {
            var userObj = new User(users[u].username, 
            					   users[u].password,
            					   users[u].firstName,
            					   users[u].lastName);
            var clone = $userRowTemplate.clone(true,true);
            clone.find(".wbdv-username").html(userObj.username);
            clone.find(".wbdv-first-name").html(userObj.firstName);
            clone.find(".wbdv-last-name").html(userObj.lastName);
            clone.attr("id", "row"+users[u].id);
            clone.find(".wbdv-edit").attr("id", users[u].id);
            $tbody.append(clone);
        }
    }
})();