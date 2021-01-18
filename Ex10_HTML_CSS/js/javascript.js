// Menu mobile
console.clear();

const app = (() => {
	let body;
	let menu;
	let menuItems;

	const init = () => {
		body = document.querySelector('body');
		menu = document.querySelector('.menu-icon');
		menuItems = document.querySelectorAll('.nav__list-item');

		applyListeners();
	}

	const applyListeners = () => {
		menu.addEventListener('click', () => toggleClass(body, 'nav-active'));
	}

	const toggleClass = (element, stringClass) => {
		if (element.classList.contains(stringClass))
			element.classList.remove(stringClass);
		else
			element.classList.add(stringClass);
	}

	init();
})();


$(document).ready(function () {
	$(".brand").slick({
		infinite: true,
		autoplay: true,
		arrows: false,
		autoplaySpeed: 2000,
		slidesToShow: 6,
		accessibility: true,
		cssEase: 'linear',
		//centerMode: true,
		responsive: [{
				breakpoint: 991,
				settings: {
					slidesToShow: 4,
				}
			},
			{
				breakpoint: 768,
				settings: {
					slidesToShow: 3,
				}
			},
			{
				breakpoint: 480,
				settings: {
					slidesToShow: 2,
				}
			}
		]
	});

	$('.slider-for').slick({
		slidesToShow: 1,
		slidesToScroll: 1,
		arrows: false,
		fade: true,
		autoplay:true,
		autoplaySpeed: 2000,
		asNavFor: '.slider-nav'
	});
	$('.slider-nav').slick({
		slidesToShow: 3,
		slidesToScroll: 1,
		asNavFor: '.slider-for',
		prevArrow:'<button id="pre"type="button"><em class="fas fa-chevron-left"></em></button>',
		nextArrow:'<button id="next" type="button"><em class="fas fa-chevron-right"></em></button>',
		focusOnSelect: true,
		variableWidth:true,
		autoplay:true,
		autoplaySpeed: 2000
	});
});

$(document).ready(function () {
	$(".item-saler").slick({
		infinite: true,
		autoplay: true,
		arrows: false,
		autoplaySpeed: 2000,
		accessibility: true,
		slidesToShow: 2,
		responsive: [{
			breakpoint: 991,
			settings: {
				slidesToShow: 1,
			}
		}, ]
	});
});

$(document).ready(function () {
	$(".slide-content").slick({
		infinite: true,
		autoplay: true,
		arrows: false,
		autoplaySpeed: 2000,
		accessibility: true,
		slidesToShow: 3,
		responsive: [{
				breakpoint: 991,
				settings: {
					slidesToShow: 2,
				}
			},
			{
				breakpoint: 768,
				settings: {
					slidesToShow: 1,
				}
			},
		]
	});
});