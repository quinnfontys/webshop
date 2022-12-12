describe('addProduct', () => {
  it('Edits the first available product', () => {
    cy.visit('http://localhost:3000/products')

    cy.get('.bi-gear-fill').first().click();

    cy.get('#formName').type(' with an edit');
    cy.get('#formDescription').type(' but adjusted to current inflation');
    cy.get('#formImage').clear().type('pineapple_edit.png');
    cy.get('#formPrice').clear().type('4.98');
    cy.get('#formCategory > .form-select').select('Apples');
    cy.get('#formStock').clear().type('98');

    // cy.get('#formSubmit').contains('Submit').click();
    cy.contains('Submit').click();


    //after refresh?? assert that item is actually added to list of items

  })
})