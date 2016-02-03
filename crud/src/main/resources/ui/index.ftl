<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Тестовой задание CRUD (Алексей Жиронкин)</title>

    <link rel="stylesheet" media="screen" href="/resources/easyui/themes/icon.css"/>
    <link rel="stylesheet" media="screen" href="/resources/easyui/themes/default/easyui.css"/>

    <script src="/resources/easyui/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="/resources/easyui/locale/easyui-lang-ru.js" type="text/javascript"></script>
    <script src="/resources/easyui/datagrid-filter.js" type="text/javascript"></script>
    <script src="/resources/easyui/jquery.edatagrid.js" type="text/javascript"></script>
    <script src="/resources/js/index.js" type="text/javascript"></script>
</head>

<body onload="init()">
    <table id="grid_users"/>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$('#grid_users').edatagrid('addRow')">Новый</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="$('#grid_users').edatagrid('destroyRow')">Удалить</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="$('#grid_users').edatagrid('saveRow')">Сохранить</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="$('#grid_users').edatagrid('cancelRow')">Отмена</a>
    </div>
</body>
</html>