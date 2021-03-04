if(document.URL.indexOf('http://localhost:8080/daily_report_system/reports/show') > -1){
    jQuery(function($){
        var _token = $('#content input[name="_token"]').val();
        var follow_id = $('#content input[name="follow_id"]');
        var follow_btn = $('#content button.follow');
        var follow_url = follow_btn.parent().attr('action');


        follow_btn.on('click', function(e){
            follow_url = follow_btn.parent().attr('action');
            $.ajax({
                url: follow_url,
                type: "POST",
                dataType: "html",
                data:{
                    "_token": _token,
                    "follow_id" : follow_id.val()
                },

                success: function () {
                    if(follow_url.indexOf('follows/create') > -1){
                        console.log('create success');
                        follow_btn.text('フォロー解除');
                        follow_btn.parent().attr('action','/daily_report_system/follows/destroy');
                        getFollow_id();
                    }else{
                        console.log('destroy success');
                        follow_btn.text('フォローする');
                        follow_btn.parent().attr('action','/daily_report_system/follows/create');
                        getFollow_id();
                    }
                },
                error: function(errorThrown){
                    console.log(errorThrown);
                }

            })
            e.preventDefault();
        })

        function getFollow_id(){
            var param = location.search;
            var follow_id = $('#content input[name="follow_id"]');
            $.ajax({
                url: "/daily_report_system/reports/show" + param,
                type: "GET",
                dataType: "html",
                success : function(data){
                    follow_id.val($(data).find('#content input[name="follow_id"]').val());
                    return follow_id;
                },
                error: function(errorThorwn){
                    console.log(errorThrown);
                }
            });
        };
    });
}

if(document.URL.indexOf('http://localhost:8080/daily_report_system/follows/show') > -1){
    jQuery(function($){
        var _token = $('#content input[name="_token"]').val();
        var follow_id = $('#content input[name="follow_id"]');
        var follow_btn = $('#content button.follow');
        var url = follow_btn.parent().attr('action');



        follow_btn.on('click', function(e){
            url = follow_btn.parent().attr('action');
            $.ajax({
                url: url,
                type: "POST",
                dataType: "html",
                data:{
                    "_token": _token,
                    "follow_id" : follow_id.val()
                },

                success: function () {
                    if(url.indexOf('create') > -1){
                        console.log('create success');
                        follow_btn.text('フォロー解除');
                        follow_btn.parent().attr('action','/daily_report_system/follows/destroy');
                        getFollow_id();
                    }else{
                        console.log('destroy success');
                        follow_btn.text('フォローする');
                        follow_btn.parent().attr('action','/daily_report_system/follows/create');
                        getFollow_id();
                    }
                },
                error: function(errorThrown){
                    console.log(errorThrown);
                }

            })
            e.preventDefault();
        })
    });

    function getFollow_id(){
        var param = location.search;
        var follow_id = $('#content input[name="follow_id"]');
        $.ajax({
            url: "/daily_report_system/follows/show" + param,
            type: "GET",
            dataType: "html",
            success : function(data){
                follow_id.val($(data).find('#content input[name="follow_id"]').val());
                return follow_id;
            },
            error: function(errorThorwn){
                console.log(errorThrown);
            }
        });
    };
}