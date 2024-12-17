async function Java_GUI_cheerpD(lib, str) {
    //make the link
    var content = str;  
    var blob = new Blob([content], {type:'text/plain'});
    var link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = "ti-notes-program.txt";
    //click the link
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}