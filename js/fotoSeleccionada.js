$(function(){
    $('#fotoTarea').on('change',function(){
    var name = $('#fotoTarea').val().split('\\').pop();
      $('#labelFotoTarea').html(name);
  });
})();