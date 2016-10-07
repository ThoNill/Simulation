package simulation.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import simulation.core.GUIFactory;
import simulation.core.Node;
import simulation.core.NodeDescription;
import simulation.core.NodeFactory;
import simulation.core.ShowResults;
import simulation.core.ShowResultsDescription;
import simulation.core.ShowResultsItem;
import simulation.core.ValueDescription;
import simulation.core.ValueDescription.Type;
import simulation.doubles.DoubleValue;
import simulation.doubles.DoubleValueDescription;
import simulation.doubles.DoubleViewFactory;
import simulation.setter.SetInToConstant;
import simulation.setter.SetInToOut;
import simulation.setter.SetInToTime;
import simulation.setter.SetParameterToConstant;
import simulation.setter.SetParameterToOut;
import simulation.setter.SetParameterToTime;
import simulation.setter.SetParameterToValue;
import simulation.setter.SetValue;
import allgemein.Check;

/**********
 * 
 * Beschreibung einer Simulation, kann eine Simulation erzeugen
 * 
 * Object
 * 
 * @author Thomas Nill
 *
 */
public class SimulationFabric extends Check {
    private static final Logger LOG = LogManager
            .getLogger(SimulationFabric.class);

    private double startTime = 0.0;
    private double endTime = 1.0;
    private double timeStep = 0.01;

    private List<NodeDescription> nodeDescriptions = new ArrayList<>();
    private List<BindDescription> bindDescriptions = new ArrayList<>();
    private List<ShowResultsDescription> resultDescriptions = new ArrayList<>();

    private HashMap<String, Node> nodeHash = new HashMap<>();
    private HashMap<String, String> boundedParameter = new HashMap<>();
    private GUIFactory<DoubleValue, DoubleValueDescription> guiFactory;
    private List<Node> nodes = new ArrayList<>();

    private ShowResultsDescription showResultdescription;

    private Simulation simulation;

    private String title;

    public SimulationFabric(String title) {
        guiFactory = new DoubleViewFactory();
        setTitle(title);
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        check(startTime >= 0.0, "Startzeit muss >= 0 sein");
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        check(endTime > 0.0, "Endzeit muss > 0 sein");
        this.endTime = endTime;
    }

    public double getTimeStep() {
        return timeStep;
    }

    public void setTimeStep(double timeStep) {
        check(timeStep > 0.0, "Schrittweite muss > 0 sein");
        this.timeStep = timeStep;
    }

    public NodeDescription addNodeDescription(String name, String className) {
        NodeDescription descr = new NodeDescription(name, className);
        nodeDescriptions.add(descr);
        return descr;
    }

    public void addBindDescription(BindDescription description) {
        checkNull(description);
        bindDescriptions.add(description);

    }

    public void connect(String ziel, String quelle) {
        String aziel[] = ziel.split("\\.");
        String aquelle[] = quelle.split("\\.");
        addBindDescription(new BindDescription(aziel[0], aziel[1], aquelle[0],
                aquelle[1]));
    }

    public void addResultDescription(String name, String className,
            String nodeName, String outName, Color color, String title) {
        ShowResultsDescription d = new ShowResultsDescription(nodeName,
                className);
        d.add(new ShowResultsItem(nodeName, nodeName, outName, color, title));
        resultDescriptions.add(d);
    }

    public Simulation createSystem() throws Exception {

        NodeFactory nodeFectory = new NodeFactory();

        simulation = new Simulation(startTime, timeStep, endTime);

        for (NodeDescription d : nodeDescriptions) {
            Node node = nodeFectory.createNode(d);
            addNode(node);
        }

        for (BindDescription d : bindDescriptions) {
            bind(d);
        }

        for (ShowResultsDescription d : resultDescriptions) {
            addShowResults(d);
        }

        return simulation;
    }

    private void addNode(Node node) {
        nodeHash.put(node.getName(), node);
        nodes.add(node);
        simulation.addNode(node);
    }

    public void bind(BindDescription bindDescription) {
        checkNull(bindDescription);

        SetValue setValue = createSetValue(bindDescription);
        switch (bindDescription.getType()) {
        case CONSTANT:
            simulation.atStart(setValue);
            break;
        case TIME:
            simulation.afterTimeStep(setValue);
            break;
        case VALUE:
            simulation.atStart(setValue);
            break;
        case NODE:
            simulation.afterCalculation(setValue);
            break;
        }
    }

    private SetValue createSetValue(BindDescription bindDescription) {
        if (bindDescription == null) {
            throw new IllegalArgumentException();
        }

        Node zielNode = nodeHash.get(bindDescription.getZielNode());

        if (zielNode == null) {
            throw new IllegalArgumentException("konnte die ZielNode "
                    + bindDescription.getZielNode() + " nicht finden");
        }

        ValueDescription ziel = zielNode.searchDescription(bindDescription
                .getZielValue());
        if (ziel == null) {
            throw new IllegalArgumentException("konnte den ZielWert "
                    + bindDescription.getZielValue() + " nicht finden");
        }

        if (ziel.getType() == Type.OUT) {
            throw new IllegalArgumentException(
                    "OUT Parameter können nicht gesetzt werden");
        }

        addToBoundParameter(ziel);

        switch (bindDescription.getType()) {
        case CONSTANT:
            switch (ziel.getType()) {
            case IN:
                return new SetInToConstant(zielNode, ziel.getNumber(),
                        bindDescription.getValue());
            case PARAMETER:
                return new SetParameterToConstant(zielNode, ziel.getNumber(),
                        bindDescription.getValue());
            case OUT:
                break;
            }
        case NODE:
            Node quellNode = nodeHash.get(bindDescription.getQuellNode());

            if (quellNode == null) {
                throw new IllegalArgumentException("konnte die QuellNode "
                        + bindDescription.getQuellNode() + " nicht finden");
            }

            ValueDescription quelle = quellNode
                    .searchDescription(bindDescription.getQuellValue());

            if (quelle == null) {
                throw new IllegalArgumentException("konnte den QuellWert "
                        + bindDescription.getQuellValue() + " nicht finden");
            }

            if (quelle.getType() != ValueDescription.Type.OUT) {
                throw new IllegalArgumentException("Quelltyp kann nur OUT sein");
            }

            switch (ziel.getType()) {
            case IN:
                return new SetInToOut(zielNode, ziel.getNumber(), quellNode,
                        quelle.getNumber());
            case PARAMETER:
                return new SetParameterToOut(zielNode, ziel.getNumber(),
                        quellNode, quelle.getNumber());
            case OUT:
                break;
            }
            break;
        case VALUE:

            break;
        case TIME:
            switch (ziel.getType()) {
            case IN:
                return new SetInToTime(zielNode, ziel.getNumber(), simulation);
            case PARAMETER:
                return new SetParameterToTime(zielNode, ziel.getNumber(),
                        simulation);
            case OUT:
                ;
            }
            break;

        }
        throw new IllegalArgumentException("Konnte nichts finden");

    }

    private void addToBoundParameter(ValueDescription valueDescription) {
        if (valueDescription.getType() == Type.PARAMETER) {
            boundedParameter.put(valueDescription.getLongName(), "ok");
        }
    }

    public ParameterPanel createParameterPanel(Simulation simulation) {
        checkNull(simulation);

        return createParameterPanel(simulation, bestimmeOffeneParameter());
    }

    private List<ValueDescription> bestimmeOffeneParameter() {
        List<ValueDescription> openParameters = new ArrayList<>();
        for (Node n : nodes) {
            openParameters.addAll(n.openParameters(boundedParameter));
        }
        return openParameters;
    }

    private ParameterPanel createParameterPanel(Simulation simulation,
            List<ValueDescription> openParameters) {
        ParameterPanel panel = new ParameterPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (ValueDescription descr : openParameters) {

            if (descr instanceof DoubleValueDescription) {
                DoubleValueDescription ddescr = (DoubleValueDescription) descr;
                DoubleValue value = new DoubleValue(descr.getLongName());
                value.addPropertyChangeListener(simulation);
                panel.add(value);
                panel.add(guiFactory.getGUI(value, ddescr));

                SetValue setValue = new SetParameterToValue(descr.getNode(),
                        descr.getNumber(), value);
                simulation.atStart(setValue);
            }
        }
        return panel;
    };

    public void addShowResults(ShowResultsDescription description) {
        checkNull(description);
        showResultdescription = description;
    }

    public JPanel resultLegende() {
        JPanel panel = new ParameterPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (ShowResultsItem descr : showResultdescription.getItems()) {

            JPanel lpanel = new JPanel();
            TitledBorder titel = new TitledBorder(descr.getTitle());
            lpanel.setBorder(titel);
            lpanel.setLayout(new BorderLayout());
            JPanel cpanel = new JPanel();
            cpanel.setBackground(descr.getColor());
            lpanel.add(cpanel, BorderLayout.NORTH);
            panel.add(lpanel);

        }
        panel.setMaximumSize(new Dimension(200, 8));
        return panel;
    };

    public ShowResults createShowResults(Simulation simulation) {
        checkNull(simulation);

        try {
            ShowResults show = createShowResults(showResultdescription);
            simulation.addShowResult(show);
            return show;
        } catch (Exception e) {
            LOG.error("Excepion in createShowResults", e);
        }
        return null;
    }

    private ShowResults createShowResults(ShowResultsDescription description)
            throws NoSuchMethodException, SecurityException,
            ClassNotFoundException, InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        Class clazz = Class.forName(description.getClassName());
        ShowResults res = (ShowResults) clazz.newInstance();

        res.init(description.getName());

        for (ShowResultsItem item : description.getItems()) {
            createOutputForItem(res, item);
        }
        return res;
    }

    private void createOutputForItem(ShowResults res, ShowResultsItem item) {
        Node node = nodeHash.get(item.getNodeName());
        ValueDescription valueDescription = node.searchDescription(item
                .getOutName());
        res.addOutputFor(item.getName(), node, valueDescription.getNumber(),
                item.getColor());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
