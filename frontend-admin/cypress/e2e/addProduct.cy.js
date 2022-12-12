describe('addProduct', () => {
  it('Adds a new product', () => {
    cy.visit('http://localhost:3000/products')

    cy.contains('Add Product').click();

    cy.get('#formName').type('Pine apple');
    cy.get('#formDescription').type('An apple, but with spikes and also a literal plant on top');
    cy.get('#formImage').type('pineapple.png');
    cy.get('#formPrice').type('3.98');
    cy.get('#formCategory').type('Bananas');
    cy.get('#formStock').type('99');

    cy.contains('Submit').click();

    // cy.get('#formSubmit').contains('Submit').click();

  })
})