window.addEventListener("DOMContentLoaded", function () {
    const couponsContainer = document.getElementById("coupons");
    let jsonData;
    let page = 1;

    function fetchCoupons() {
        if (!jsonData) {
            fetch('data.json')
                .then(response => response.json())
                .then(data => {
                    jsonData = data;
                    addCouponsToContainer();
                })
                .catch(error => console.error('Error fetching data:', error));
        } else {
            addCouponsToContainer();
        }
    }

    function addCouponsToContainer() {
        const coupons = jsonData.coupons.slice((page - 1) * 4, page * 4);
        if (coupons.length === 0) {
            window.removeEventListener('scroll', handleScroll);
        } else {
            coupons.forEach(coupon => {
                const couponDiv = document.createElement('div');
                couponDiv.classList.add('coupon');

                const img = document.createElement('img');
                img.src = coupon.image;
                img.alt = coupon.description;
                couponDiv.appendChild(img);

                const name = document.createElement('p');
                name.textContent = 'Name: ' + coupon.name;
                couponDiv.appendChild(name);

                const description = document.createElement('p');
                description.textContent = coupon.description;
                couponDiv.appendChild(description);

                const price = document.createElement('p');
                price.textContent = 'Price: ' + coupon.price;
                couponDiv.appendChild(price);

                const validUntil = document.createElement('p');
                validUntil.textContent = 'Valid until: ' + coupon.validUntil;
                couponDiv.appendChild(validUntil);

                couponsContainer.appendChild(couponDiv);
            });

            page++;
        }
    }

    fetchCoupons();

    const handleScroll = function () {
        const B = document.body,
            DE = document.documentElement,
            O = Math.min(B.clientHeight, DE.clientHeight);

        if (!O) {
            O = B.clientHeight;
        }

        const S = Math.max(B.scrollTop, DE.scrollTop),
            C = Math.max(B.scrollHeight, DE.scrollHeight);

        if (O + S == C) {
            fetchCoupons();
        }
    };

    window.addEventListener('scroll', handleScroll);

    const searchInput = document.getElementById('search');
    searchInput.addEventListener('input', function (event) {
        const searchTerm = event.target.value.toLowerCase();
        const allCoupons = document.querySelectorAll('.coupon');

        allCoupons.forEach(coupon => {
            const name = coupon.querySelector('p').textContent.toLowerCase();
            if (name.includes(searchTerm)) {
                coupon.style.display = 'block';
            } else {
                coupon.style.display = 'none';
            }
        });
    }); 
});
