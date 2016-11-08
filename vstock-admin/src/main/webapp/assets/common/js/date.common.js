/**
 * Created by Ligang on 2016/9/20.
 */
function getDatePic(div1,div2){
    $('.dateStartTime').calendar({
        trigger: div1,
        zIndex: 999,
        format: 'yyyy-mm-dd',
        onSelected: function (view, date, data) {
            console.log('event: onSelected')
        },
        onClose: function (view, date, data) {
            console.log('event: onClose');
            console.log('view:' + view);
            console.log('date:' + date);
            console.log('data:' + (data || 'None'));
        }
    });
    $('.dateEndTime').calendar({
        trigger: div2,
        zIndex: 999,
        format: 'yyyy-mm-dd',
        onSelected: function (view, date, data) {
            console.log('event: onSelected')
        },
        onClose: function (view, date, data) {
            console.log('event: onClose');
            console.log('view:' + view);
            console.log('date:' + date);
            console.log('data:' + (data || 'None'));
        }
    });
}