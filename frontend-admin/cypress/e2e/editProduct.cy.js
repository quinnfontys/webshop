describe('editProduct', () => {
  it('Edits the first available product', () => {
    cy.visit('http://localhost:3002/products')

    cy.get('.bi-gear-fill').first().click();

    cy.get('#formName').type(' with an edit');
    cy.get('#formDescription').type(' but adjusted to current inflation');
    cy.get('#formPrice').clear().type('4.98');
    cy.get('#formStock').clear().type('98');

    cy.intercept({
      method: 'PUT',
      url: 'http://localhost:8090/api/product',
    }).as('apiCheck')
    
    // click submit button to save edits
    cy.contains('Submit').click();
    
    cy.wait('@apiCheck').then((interception) => {
      assert.isNotNull(interception.response.body, "call has data");
      assert.equal(interception.response.statusCode, 200, "correct http response was given");
    }) 

    //assert ish
    // cy.get('#column > td').eq(0).should('have.text', 'Gala with an edit');
    // cy.get('#column > td').eq(1).should('have.text', 'An apple :D but adjusted to current inflation');
    // cy.get('#column > td').eq(2).should('have.text', '4.98');
    // cy.get('#column > td').eq(4).should('have.text', '98');

  })
})