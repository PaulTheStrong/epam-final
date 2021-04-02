var searchBlocks = document.getElementsByClassName("search-block");
Array.from(searchBlocks).forEach(function(element) {
    element.getElementsByTagName("h4")[0].addEventListener('click', show);
});

function show() {
    console.log(this.parentElement);
    let listElement = this.parentElement.getElementsByTagName("ul")[0];
    if (listElement.style.maxHeight){
        listElement.style.maxHeight = null;
    } else {
        listElement.style.maxHeight = listElement.scrollHeight + "px";
    }
}