function AdminUserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.searchUser = searchUser;
    this.url = 'https://young-dusk-43482.herokuapp.com/api/user';
    var self = this;
    function createUser(user) {
    	return fetch(this.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
    	}).then(function(response) {
            return response.json();
        });
    }
    function findAllUsers() {
        return fetch(this.url)
            .then(function(response) {
                return response.json();
            });
    }
    function findUserById(userId, callback) { }
    
    function updateUser(userId, user) {
    	return fetch(this.url+"/"+userId, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
    	}).then(function(response) {
            return response.json();
        });
    }
    
    function deleteUser(userId) {
    	return fetch(this.url+"/delete/"+userId, {
            method: 'post',
            headers: {
                'content-type': 'application/json'
            }
    	}).then(function(response) {
            return null;
        });
    }
    function searchUser(user) {
    	return fetch(this.url+"/search", {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
    	}).then(function(response) {
    		return response.json();
        });
    }
}