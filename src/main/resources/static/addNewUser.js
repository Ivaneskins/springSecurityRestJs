getAllRoles('role');

let addNewUserBtn = document.getElementById('addNewUser');
addNewUserBtn.addEventListener('click', function(e) {
    e.preventDefault();
    addNewUser();
    setTimeout(function () {
        hideAddUserAndShowUserTable();
    }, 100);
})