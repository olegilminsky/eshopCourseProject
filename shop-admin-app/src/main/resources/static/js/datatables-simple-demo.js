window.addEventListener('DOMContentLoaded', event => {
    // DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

    const datatablesSimple = document.getElementById('datatablesProducts');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple);
    }
});
