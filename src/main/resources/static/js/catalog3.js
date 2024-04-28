const carousel3 = document.querySelector(".carousel3");
const arrowBtns3 = document.querySelectorAll(".wrapper3 .arrows3");
const firstCardWidth3 = carousel3.querySelector(".card").offsetWidth;
const carouselChildren3 = [...carousel3.children];

let isDragging3 = false, startX3, startScrollLeft3;

let cardPerView3 = Math.round(carousel3.offsetWidth / firstCardWidth3);

carouselChildren3.slice(-cardPerView3).reverse().forEach(card => {
    carousel3.insertAdjacentHTML("afterbegin", card.outerHTML);
});

carouselChildren3.slice(0, cardPerView3).forEach(card => {
    carousel3.insertAdjacentHTML("beforeend", card.outerHTML);
});

arrowBtns3.forEach(btn => {
    btn.addEventListener("click", () => {
        carousel3.scrollLeft += btn.id === "left3" ? -firstCardWidth3 : firstCardWidth3;
    })
});
const dragStart3 = (e) =>{
    isDragging3 = true;
    carousel3.classList.add("dragging3")
    startX3 = e.pageX;
    startScrollLeft3 = carousel3.scrollLeft;
}
const dragging3 = (e) =>{
    if(!isDragging3) return;
    carousel3.scrollLeft = startScrollLeft3 - (e.pageX - startX3);
}
const dragStop3 = () =>{
    isDragging3 = false;
    carousel3.classList.remove("dragging3");
}
const infiniteScroll3 = () => {
    if(carousel3.scrollLeft === 0)
    {
        carousel3.classList.add("no-transition3");
        carousel3.scrollLeft = carousel3.scrollWidth - (2 * carousel3.offsetWidth);
        carousel3.classList.remove("no-transition3");
    }else if (Math.ceil(carousel3.scrollLeft) === carousel3.scrollWidth - carousel3.offsetWidth) {
        carousel3.classList.add("no-transition3");
        carousel3.scrollLeft = carousel3.offsetWidth;
        carousel3.classList.remove("no-transition3");
    }
}
carousel3.addEventListener("mousedown", dragStart3);
carousel3.addEventListener("mousemove", dragging3);
document.addEventListener("mouseup", dragStop3);
carousel3.addEventListener("scroll", infiniteScroll3);
