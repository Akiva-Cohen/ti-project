console.log("script2");
//wait for it to load in
starter();
function starter() {
    window.addEventListener("load", function () {
        console.log("loaded");
        start();
    });
}
function start() {
        this.setTimeout(function () {
            if (this.javax && this.javax.swing) {
                console.log("swing detected");
                this.java.io.FileWriter = function (path) {
                    //downloading from browser
                    console.log("triggered");
                    replace();
                }
                this.javax.swing.JFileChooser.prototype.showSaveDialog = function(parent) {
                    console.log("trigerswing");
                    replace();
                }
            } else {
                console.log("no swing");
                start();
            }
        },2000);
}

function replace() {
    console.log("called")
    //make the link
    var content = "test_content";
    var blob = new Blob([content], {type:'text/plain'});
    var link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = "testfile.txt";
    //click the link
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}