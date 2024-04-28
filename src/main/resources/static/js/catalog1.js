const carousel = document.querySelector(".carousel1");
const arrowBtns = document.querySelectorAll(".wrapper1 .arrows1");
const firstCardWidth = carousel.querySelector(".card").offsetWidth;
const carouselChildren = [...carousel.children];

let isDragging = false, startX, startScrollLeft;

let cardPerView = Math.round(carousel.offsetWidth / firstCardWidth);

carouselChildren.slice(-cardPerView).reverse().forEach(card => {
    carousel.insertAdjacentHTML("afterbegin", card.outerHTML);
});

carouselChildren.slice(0, cardPerView).forEach(card => {
    carousel.insertAdjacentHTML("beforeend", card.outerHTML);
});

arrowBtns.forEach(btn => {
    btn.addEventListener("click", () => {
        carousel.scrollLeft += btn.id === "left1" ? -firstCardWidth : firstCardWidth;
    })
});
const dragStart = (e) =>{
    isDragging = true;
    carousel.classList.add("dragging1")
    startX = e.pageX;
    startScrollLeft = carousel.scrollLeft;
}
const dragging = (e) =>{
    if(!isDragging) return;
    carousel.scrollLeft = startScrollLeft - (e.pageX - startX);
}
const dragStop = () =>{
    isDragging = false;
    carousel.classList.remove("dragging1");
}
const infiniteScroll = () => {
    if(carousel.scrollLeft === 0)
    {
        carousel.classList.add("no-transition1");
        carousel.scrollLeft = carousel.scrollWidth - (2 * carousel.offsetWidth);
        carousel.classList.remove("no-transition1");
    }else if (Math.ceil(carousel.scrollLeft) === carousel.scrollWidth - carousel.offsetWidth) {
        carousel.classList.add("no-transition1");
        carousel.scrollLeft = carousel.offsetWidth;
        carousel.classList.remove("no-transition1");
    }
}
carousel.addEventListener("mousedown", dragStart);
carousel.addEventListener("mousemove", dragging);
document.addEventListener("mouseup", dragStop);
carousel.addEventListener("scroll", infiniteScroll);
