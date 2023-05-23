$(window).on("load", function() {
  $(".loader *").fadeOut();
  $(".loader").delay(200).fadeOut("slow");
});

window.onload=function() {
  var card = document.getElementById('activator');
  var tl = gsap.timeline({defaults: {ease: "power2.in"}});

  var active = false;

  tl.to('.activator', {
      duration: 0.25,
      background: '#400008',
      'borderRadius': '5em 5em 0 0'
  });
  tl.to('.dropdown-menu', {duration: 0, display: 'flex'})
  tl.from('.dropdown-menu', {
    duration: 1,
    opacity: 0
  });
  tl.from('.dropdown-menu a', {
            opacity: 0,
            x: "5em",
            stagger: 0.25
        }, '-=1');
  tl.pause();

  card.addEventListener('click', () => {
      active = !active;
      if (active) {
        tl.play();
        card.classList.add("active");
      }
      else {
        tl.reverse();
        card.classList.remove("active");
      }
  })
  $(document).click(function(event) {
    var $target = $(event.target);
    if(!$target.closest('#dropdown-menu').length &&
    $('#dropdown-menu').is(":visible")) {
      active = false;
      tl.reverse();
      card.classList.remove("active");
    }
  });
}