
(function($,obj){

    obj.table_obj = null;

    obj.init_table = function(id,options)
    {
        var _self = this;
        var opt_table = $.extend({
                "pageLength": 50,
                columnDefs: [

                ],
                stateSave:false,
                rowReorder:{
                    selector: 'tr'//,
                    //dataSrc: 0
                },
                fnRowCallback: function (nRow, aData, iDisplayIndex) {
                    nRow.setAttribute('id', aData[1]);
                }

            },options),

            table_node_id = $(id).attr('id'),
            table_node = $('#'+table_node_id);


        this.table_obj = table_node.dataTable(opt_table);
        table_node.on('draw.dt',function(){
            table_node.find('thead th').each(function (i) {
                if(opt.is_sorting)
                    $(this).removeClass('sorting');
            });
        });
        table_node.on('init.dt',function(e, settings, data){
            //console.log('init_table');
        });

            this.table_obj.api().rowReorder.disable();

    };

    var isEmpty = function(str){
        return !str || str.length === 0;
    };



})(jQuery,window.customer_tables = window.customer_tables||{});