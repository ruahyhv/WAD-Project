document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll('.item-quantity').forEach(function(element) {
        element.addEventListener('change', function() {
            var itemId = this.closest('.cart-item').dataset.productId;
            var quantity = this.value;
            
            fetch('/cart/update', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ itemId: itemId, quantity: quantity })
            })
            .then(response => response.json())
            .then(response => {
                // Update the total price
                document.querySelector('#total-price span').textContent = response.totalPrice.toFixed(2);
                
                // Update the price for the specific item
                var itemPrice = response.itemPrices[itemId];
                this.closest('.cart-item').querySelector('.item-price').textContent = itemPrice.toFixed(2);
            })
            .catch(error => {
                console.error('Error updating cart:', error);
            });
        });
    });
});
