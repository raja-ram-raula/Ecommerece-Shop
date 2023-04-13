function uploadFile() {
  var file = document.getElementById("fileOb");
  var form = new FormData();
  form.append("image", file.files[0]);
  var inputs = {
    url: "https://api.imgbb.com/1/upload?key=c41495c39db4f2a91e59232ed6dd47a3",
    method: "POST",
    timeout: 0,
    processData: false,
    mimeType: "multipart/form-data",
    contentType: false,
    data: form,
  };

  $.ajax(inputs).done(function (response) {
    var job = JSON.parse(response);
    $("#imageLink").val(job.data.url);
  });
}