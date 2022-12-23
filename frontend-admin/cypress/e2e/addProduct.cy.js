describe('addProduct', () => {
  it('Adds a new product', () => {
    cy.visit('http://localhost:3002/products')

    cy.contains('Add Product').click();

    cy.get('#formName').type('Pine apple');
    cy.get('#formDescription').type('An apple, but with spikes and also a literal plant on top');
    cy.get('#formImage').type('pineapple.png');
    cy.get('#formPrice').type('3.98');
    cy.get('#formCategory').type('Bananas');
    cy.get('#formStock').type('99');

    cy.intercept({
      method: 'POST',
      url: 'http://localhost:8090/api/product',
    }).as('apiCheck')
    
    cy.contains('Submit').click();
    
    cy.wait('@apiCheck').then((interception) => {
      assert.isNotNull(interception.response.body, "call has data");
      assert.equal(interception.response.statusCode, 201, "correct http response was given");
    }) 
  })
})