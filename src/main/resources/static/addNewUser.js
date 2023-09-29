getAllRoles('role');

let addNewUserBtn = document.getElementById('addNewUser');
addNewUserBtn.addEventListener('click', function(e) {
    e.preventDefault();
    addNewUser();
    setTimeout(function () {
        // window.location.href='http://localhost:8080/admin/';
        hideAddUserAndShowUserTable();
    }, 100);
})