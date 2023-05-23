document.addEventListener("DOMContentLoaded", () => {
	var sidebar = document.querySelector('.sidebar__main');
	var active = true;
	$(document).click(function(event) {
		var target = event.target;
		if (!active && (target.tagName == "BODY" || target.tagName == "SECTION" || target.tagName == "HEADER" || target.tagName == "FOOTER" || target.classList.contains('items')))
		{
			sidebar.style.display = "block";
			active = !active;
		}
		else if (active && (target.tagName == "BODY" || target.tagName == "SECTION" || target.tagName == "HEADER" || target.tagName == "FOOTER" || target.classList.contains('items')))
		{
			active = !active;
			sidebar.style.display = "none";
		}
	});
	jQuery(function($) {
      $('.sidebar__min').on('input', function() {
        const url = new URL(location.href);
        if ($('.sidebar__min').val() == 0)
            url.searchParams.delete("min");
        else
            url.searchParams.set("min", $('.sidebar__min').val());
        location.href = url.href;
      });
      $('.sidebar__max').on('input', function() {
          const url = new URL(location.href);
          if ($('.sidebar__max').val() == 9999)
              url.searchParams.delete("max");
          else
              url.searchParams.set("max", $('.sidebar__max').val());
          location.href = url.href;
      });
      $('.sidebar__size').on('input', function() {
                const url = new URL(location.href);
                if ($('.sidebar__size').val() != "" && $('.sidebar__size').val() < 9999 && $('.sidebar__size').val() >= 0)
                    url.searchParams.set("size", $('.sidebar__size').val());
                else
                    url.searchParams.delete("size");
                location.href = url.href;
            });
      $('.sidebar__select').on('change', function() {
        var select = document.querySelector('.sidebar__select');
        const url = new URL(location.href);
        if (select.options[select.selectedIndex].value == "")
            url.searchParams.delete("type");
        else
            url.searchParams.set("type", select.options[select.selectedIndex].value);
        location.href = url.href;
      });
      $('.sorting__select').on('change', function() {
          var select = document.querySelector('.sorting__select');
          const url = new URL(location.href);
          if (select.options[select.selectedIndex].value == "id")
              url.searchParams.delete("sortBy");
          else
              url.searchParams.set("sortBy", select.options[select.selectedIndex].value);
          location.href = url.href;
      });
    });
});