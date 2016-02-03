

function init() {
    var grid = $('#grid_users').edatagrid({
        saveUrl: 'user_create',
        url: 'user_read',
        updateUrl: 'user_update',
        destroyUrl: 'user_delete',
        method: 'get',
        idField: 'id',
        remoteFilter: true,
        fitColumns: true,
        border: false,
        singleSelect: true,
        rownumbers: true,
        pagination: true,
        pageList: [10, 30, 50, 100],
        pageSize: 30,
        fit: true,
        columns: [
            [
                {
                    field: 'id',
                    title: 'ID',
                    hidden: true
                },

                {
                    field: 'name',
                    title: 'Имя',
                    width: 100,
                    sortable: true,
                    editor: {
                        type: 'validatebox',
                        options: {
                            required: true
                        }
                    }
                },

                {
                    field: 'age',
                    title: 'Возраст',
                    align: 'center',
                    sortable: true,
                    editor: {
                        type: 'numberbox',
                        options: {
                            required: true,
                            precision: 0
                        }
                    }
                },

                {
                    field: 'isAdmin',
                    title: 'Администратор',
                    align: 'center',
                    sortable: true,
                    editor: {
                        type: 'checkbox',
                        options: {
                            on: 'да',
                            off: 'нет'
                        }
                    }
                },

                {
                    field: 'createDate',
                    title: 'Дата создания',
                    align: 'center',
                    sortable: true,
                    editor: {
                        type: 'datebox',
                        options: {
                            required: true
                        }
                    }
                }
            ]
        ],
        toolbar: '#toolbar'
    });

    grid.datagrid('enableFilter', [
        {
            field: 'name',
            type: 'text'
        }, {
            field: 'age',
            type: 'numberbox',
            options: {precision: 0},
            op: ['equal', 'less', 'greater']
        }, {
            field: 'isAdmin',
            type: 'combobox',
            options:{
                panelHeight:'auto',
                data: [{value: 'true', text: 'да'}, {value: 'false', text: 'нет'}, {value: 'all', text: 'все'}],
                onChange: function(value) {
                    console.log(value);
                    if (value == null || value == 'all' || value == '') {
                        $('#grid_users').datagrid('removeFilterRule', 'isAdmin');
                    } else {
                        $('#grid_users').datagrid('addFilterRule', {
                            field: 'isAdmin',
                            op: 'equal',
                            value: value
                        });
                    }
                    $('#grid_users').datagrid('doFilter');
                }
            }
        }, {
            field: 'createDate',
            type: 'datebox',
            op: ['equal', 'less', 'greater']
        }]);
}

// contains,equal,notequal,beginwith,endwith,less,lessorequal,greater,greaterorequal

// label,text,textarea,checkbox,numberbox,validatebox,datebox,combobox,combotree