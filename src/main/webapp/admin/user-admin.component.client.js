(function(){
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    $(main);

    function main() {
        $usernameFld = $("#usernameFld");
        $firstNameFld = $("#firstNameFld");
        $userRowTemplate = $(".wbdv-template");
        $tbody = $("tbody");
        
        $usernameFld.val("alice");
        
        userService
            .findAllUsers()
            .then(renderUsers);
    }
    function createUser() {  }
    function findAllUsers() {  }
    function findUserById() {  }
    function deleteUser() {  }
    function selectUser() {  }
    function updateUser() {  }
    function renderUser(user) {  }
    function renderUsers(users) {
        for(var u=0; u<users.length; u++) {
            console.log(users[u]);
            var clone = $userRowTemplate.clone();
            clone.find(".wbdv-username").html(users[u].username);
            clone.find(".wbdv-first-name").html(users[u].firstName);
            clone.find(".wbdv-last-name").html(users[u].lastName);
            clone.find(".wbdv-role").html(users[u].role);
            $tbody.append(clone);
        }
    }
})();