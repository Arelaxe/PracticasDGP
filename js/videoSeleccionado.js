$(function(){
    $('#videoTarea').on('change',function(){
    var name = $('#videoTarea').val().split('\\').pop();
      $('#labelVideoTarea').html(name);
  });
})();