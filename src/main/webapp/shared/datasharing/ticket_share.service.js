app.factory('TicketShare', function() {

  var ticket = {};

  function set(data) {
    ticket = data;
  }
  function get() {
    return ticket;
  }

  return {
    set: set,
    get: get
  }

});