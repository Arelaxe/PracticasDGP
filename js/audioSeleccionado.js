$(function(){
    $('#audioTarea').on('change',function(){
    var name = $('#audioTarea').val().split('\\').pop();
      $('#labelAudioTarea').html(name);
  });
})();